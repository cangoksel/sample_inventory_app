package com.github.cangoksel.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.github.cangoksel.user.User;

import java.util.Collection;

/**
 * Created by tozyurek on 17.04.2015.
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SecurityContextBean {
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
