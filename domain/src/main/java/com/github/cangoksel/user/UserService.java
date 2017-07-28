package com.github.cangoksel.user;

import com.github.cangoksel.user.model.KullaniciViewModel;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by herdemir on 03.02.2015.
 */
public interface UserService {
    void resetPassword(String email, String url);

    void changePassword(String eskiSifre, String yeniSifre);

    void changePassword(UUID kullaniciID, String yeniSifre);

    @EventListener({AuthenticationSuccessEvent.class, InteractiveAuthenticationSuccessEvent.class})
    void onAuthenticationSuccess(AbstractAuthenticationEvent event);

    void setLoginedKullanici(String userName);

    Kullanici fetchCurrentUser();

    Boolean hasKullaniciByEposta(String kullaniciEposta);

    List<Kullanici> completeKullanici(String query);

    Kullanici fetchKullaniciByEposta(String username);

    Kullanici fetchKullaniciWithRolYetki(Kullanici kullanici);

    Kullanici fetchKullaniciWithRol(Kullanici kullanici);

    Kullanici kullaniciKaydet(KullaniciViewModel kullanici);

    Kullanici save(Kullanici kullanici);

    List<Kullanici> roleAitKullaniciList(UUID rolId);

    List<Kullanici> completeAllKullanici(String query);

    Collection<Kullanici> findKullanicilarByMusteriId(UUID id);

    List<Kullanici> findByKullaniciIdList(List<UUID> kullaniciIdList);

    void ldapKulaniciEslestir(String eposta, String ldapEposta);

    Kullanici findOne(UUID id);

    void updateUserContext();

    Kullanici fetchJobUser();

    boolean checkValidIdAndToken(UUID kullaniciId, UUID token);

    Token createAccessToken(KullaniciInfo principal);
}
