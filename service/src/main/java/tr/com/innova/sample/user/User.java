package tr.com.innova.sample.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.innova.sample.common.utils.LocaleUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by herdemir on 03.02.2015.
 */
public class User implements UserDetails {

    public static final SimpleGrantedAuthority ROLE_USER_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");

    private final String username;
    private final String password;
    private final String name;
    private final String lastname;
    private final boolean enabled;
    private final boolean locked;
    private final boolean expired;
    private final boolean credentialsExpired;
    private String loginServerAdress;
    private LocalDateTime lastLogin;

    final private List<GrantedAuthority> authorities;

    public User(Kullanici kullanici) {
        this.username = kullanici.getEposta();
        this.password = kullanici.getSifre();
        this.name = kullanici.getAd();
        this.lastname = kullanici.getSoyad();

        List<GrantedAuthority> authorities = kullanici.getRoller().stream()
                                                      .map(r -> new SimpleGrantedAuthority(r.getKod()))
                                                      .collect(Collectors.toList());

        authorities.addAll(kullanici.getRoller()
                                    .stream()
                                    .flatMap(r -> r.getYetkiler().stream())
                                    .collect(Collectors.toList())
                                    .stream()
                                    .map(y -> new SimpleGrantedAuthority(y.getKod()))
                                    .filter(y -> !authorities.contains(y))
                                    .collect(Collectors.toList()));

        authorities.add(ROLE_USER_AUTHORITY);

        addJcrRoles(authorities);

        this.authorities = authorities;

        this.enabled = kullanici.isHesapEtkin();
        this.locked = kullanici.isHesapKilitli();
        this.expired = false;
        this.credentialsExpired = false;
    }

    private void addJcrRoles(final List<GrantedAuthority> authorities) {
        authorities.add(new SimpleGrantedAuthority("readwrite"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        final String lastname = this.lastname == null ? "" : " " + this.lastname.toUpperCase(LocaleUtils.TURKISH);
        return this.name + lastname;
    }

    public String getLoginServerAdress() {
        return loginServerAdress;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void login(String loginServerAdress) {
        lastLogin = LocalDateTime.now();
        this.loginServerAdress = loginServerAdress;
    }
}
