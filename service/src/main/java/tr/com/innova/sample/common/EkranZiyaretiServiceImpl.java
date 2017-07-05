package tr.com.innova.sample.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.innova.sample.common.entity.EntityRepository;
import tr.com.innova.sample.sistem.EkranZiyareti;
import tr.com.innova.sample.sistem.EkranZiyaretiService;
import tr.com.innova.sample.user.User;

import java.time.LocalDateTime;

/**
 * Created by herdemir on 24/02/15.
 */
@Service("ekranZiyaretiService")
@Transactional
public class EkranZiyaretiServiceImpl implements EkranZiyaretiService {
    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private SecurityContextBean securityContextBean;

    @Override
    public <T> void ekraniZiyaretEt(Class<T> viewClass) {
        final Object principal = securityContextBean.getAuthentication().getPrincipal();
        String username;
        if (principal instanceof User) {
            username = ((User) principal).getUsername();
        } else {
            username = (String) principal;
        }

        entityRepository.save(new EkranZiyareti(LocalDateTime.now(), viewClass.getName(), username));
    }
}
