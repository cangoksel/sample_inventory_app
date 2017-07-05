package com.github.cangoksel.sample.user;

import com.github.cangoksel.user.KullaniciInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

/**
 * Created by herdemir on 14.10.2015.
 */
@Component
@Scope(value = "session", proxyMode = TARGET_CLASS)
public class CurrentUserContext {
    private KullaniciInfo kullanici;

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public KullaniciInfo getKullanici() {
        return kullanici;
    }

    protected void setKullanici(final KullaniciInfo kullanici) {
        this.kullanici = kullanici;
    }
}
