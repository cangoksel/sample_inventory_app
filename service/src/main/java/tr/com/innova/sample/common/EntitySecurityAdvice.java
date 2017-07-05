package tr.com.innova.sample.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tr.com.innova.sample.common.entity.SecuredEntity;
import tr.com.innova.sample.common.exceptions.IllegalEntityAccessException;
import tr.com.innova.sample.user.CurrentUserContext;
import tr.com.innova.sample.user.KullaniciInfo;

import java.util.Collection;

/**
 * Created by herdemir on 09.09.2015.
 */
@Component
public class EntitySecurityAdvice implements Ordered {
    @Value("1")
    private int order;

    @Autowired
    private CurrentUserContext currentUserContext;

    @Override
    public int getOrder() {
        return order;
    }

    public Object execute(final ProceedingJoinPoint call) throws Throwable { //NOSONAR
        final Object returnValue;
        returnValue = call.proceed();

        // TODO: [HE] Batch işlemlerde kullanıcı kontrolu yapmamak için çakma bir çözüm
        //            authentication objesinin null olması sadece Servlet Request olmayan threadlarda oluyor.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "emsal".equals(authentication.getPrincipal())) {
            return returnValue;
        }

        final KullaniciInfo kullanici = currentUserContext.getKullanici();

        if (kullanici == null || kullanici.isSistemAdminOrDestekUser()) {
            return returnValue;
        }

        if (returnValue instanceof SecuredEntity) {
            checkSecureEntity(returnValue, kullanici);
        } else if (returnValue instanceof Collection) {
            final Collection collection = (Collection) returnValue;
            if (!collection.isEmpty() && collection.iterator().next() instanceof SecuredEntity) {
                for (final Object entityObject : collection) {
                    checkSecureEntity(entityObject, kullanici);
                }
            }
        }
        return returnValue;
    }

    private void checkSecureEntity(final Object entityObject, final KullaniciInfo kullanici) {
        final SecuredEntity securedEntity = (SecuredEntity) entityObject;
        if (!kullanici.getId().equals(securedEntity.getId())) {
            throw new IllegalEntityAccessException("Yetkiniz yok");
        }
    }
}
