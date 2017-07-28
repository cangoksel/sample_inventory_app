package com.github.cangoksel.oauth.login;

import com.github.cangoksel.user.*;
import com.github.cangoksel.user.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gcan on 28.07.2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    /**
     * Retrieves the currently logged in user.
     *
     * @return A transfer containing the username and the roles.
     */

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON)
    public Kullanici getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        UserDetails userDetails = (UserDetails) principal;

        return userService.fetchKullaniciByEposta(userDetails.getUsername());
    }

    /**
     * Authenticates a user and creates an access token.
     *
     * @param username The name of the user.
     * @param password The password of the user.
     * @return The generated access token.
     */


    @PostMapping(path = "/authenticate", produces = "application/json;charset=UTF-8")
    public Token authenticate(@RequestBody UserBilgi info) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        Kullanici kullanici = userService.fetchKullaniciByEposta(((User) principal).getUsername());
        return this.userService.createAccessToken((KullaniciInfo) kullanici);
    }

    private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }
}