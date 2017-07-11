package com.github.cangoksel.user;

import com.github.cangoksel.common.entity.AbstractEntity;
import com.github.cangoksel.common.exceptions.DomainException;
import com.github.cangoksel.mail.MailService;
import com.github.cangoksel.user.model.KullaniciViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.cangoksel.common.SecurityContextBean;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author GC
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String DEFAULT_SIFRE = "123456";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private SecurityContextBean securityContextBean;

    @Autowired
    private CurrentUserContext currentUserContext;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenRepository tokenRepository;

    protected UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository,
        RolService rolService,
        SecurityContextBean securityContextBean) {
        this.rolService = rolService;
        this.userRepository = userRepository;
        this.securityContextBean = securityContextBean;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Kullanici kullanici = userRepository.findByEposta(username);

        if (kullanici == null) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }

        return new User(kullanici);
    }

    @Override
    public void resetPassword(final String email, String url) {
        final Kullanici kullanici = userRepository.findByEposta(email);
        if (kullanici == null || kullanici.isDeleted() || !kullanici.isHesapEtkin() || kullanici.isHesapKilitli()) {
            LOGGER.error("Cannot find com.github.cangoksel.user with email {}", email);
            throw new DomainException("Eposta adresi bulunamadı.");
        }

        Token token = tokenRepository.save(new Token(kullanici));
        mailService.sendMail(kullanici.getEposta(), "Şifre sıfırlama linki:", "Şifrenizi sıfırlamak için : " + url
                                                                              + "/parolaSifirla?id=" +
                                                                              kullanici.getId() + "&token=" +
                                                                              token.getValue());

        LOGGER.debug("Password reset for {}", email);
    }

    @Override
    public void changePassword(String eskiSifre, String yeniSifre) {
        final Kullanici kullanici = fetchCurrentUser();
        kullanici.sifreDegistir(eskiSifre, yeniSifre);
    }

    @Override
    public void changePassword(UUID kullaniciID, String yeniSifre) {
        final Kullanici kullanici = userRepository.findOne(kullaniciID);
        kullanici.sifreOlustur(yeniSifre);
        userRepository.save(kullanici);

        tokenRepository.findOne(kullanici.getId()).ifPresent(token1 -> {
            token1.delete();
            tokenRepository.save(token1);
        });
    }

    @Override
    public void onAuthenticationSuccess(final AbstractAuthenticationEvent event) {
        final User user = ((User) event.getAuthentication().getPrincipal());
        setLoginedKullanici(user.getUsername());
    }

    @Override
    public void setLoginedKullanici(String userName) {
        final Kullanici kullanici = userRepository.findByEposta(userName);
        kullanici.updateSuccesfulLogin();
        currentUserContext.setKullanici(kullanici);
    }

    @Override
    public Kullanici fetchCurrentUser() {
        final User user = securityContextBean.getCurrentUser();
        if (user == null) {
            return null;
        }
        return userRepository.findByEposta(user.getUsername());
    }



    @Override
    public Boolean hasKullaniciByEposta(String kullaniciEposta) {
        return null != userRepository.findByEposta(kullaniciEposta);
    }

    @Override
    public List<Kullanici> completeKullanici(String query) {
        return userRepository.findByAdSoyadContainingIgnoreCaseAndNotIn(query, fetchCurrentUser().getId());
    }

    @Override
    public Kullanici fetchKullaniciByEposta(String eposta) {
        return userRepository.findByEposta(eposta);
    }

    @Override
    public Kullanici fetchKullaniciWithRolYetki(Kullanici kullanici) {
        return userRepository.fetchKullaniciWithRolYetki(kullanici.getEposta());
    }

    @Override
    public Kullanici fetchKullaniciWithRol(Kullanici kullanici) {
        return userRepository.fetchKullaniciWithRol(kullanici.getEposta());
    }

    @Override
    public Kullanici kullaniciKaydet(KullaniciViewModel model) {
        Kullanici kul1 = userRepository.findByEposta(model.getEposta());
        if (kul1 != null) {
            if (!kul1.isHesapEtkin()) {
                if (model.getRolList().isEmpty()) {
                    throw new DomainException(Kullanici.EN_AZ_BIR_ROL_SECIMI_YAPILMALIDIR);
                }
                kul1.hesapYenidenAc(model.getAd(), model.getSoyad(), model.getRolList());
                return userRepository.save(kul1);
            } else {
                throw new DomainException("Bu eposta adresi başka bir kullanıcı tarafından kullanılmaktadır.");
            }
        }
        final List<Rol> eklenecekRoller = model.getRolList().stream()
                                               .map(AbstractEntity::getId)
                                               .map(m -> rolRepository.getOne(m))
                                               .collect(Collectors.toList());
        if (eklenecekRoller.isEmpty()) {
            throw new DomainException(Kullanici.EN_AZ_BIR_ROL_SECIMI_YAPILMALIDIR);
        }

        Kullanici kullanici = new Kullanici.Builder()
            .ad(model.getAd())
            .soyad(model.getSoyad())
            .eposta(model.getEposta())
            .addRol(eklenecekRoller)
            .sifre(model.getSifre())
            .username(model.getUsername())
            .build();
        kullanici = userRepository.save(kullanici);
        return kullanici;
    }

    @Override
    public Kullanici save(Kullanici kullanici) {
        if (kullanici.isHesapEtkin() && kullanici.getRoller().isEmpty()) {
            throw new DomainException(Kullanici.EN_AZ_BIR_ROL_SECIMI_YAPILMALIDIR);
        }

        kullanici = userRepository.save(kullanici);

        if (kullanici.getId().equals(currentUserContext.getKullanici().getId())) {

            currentUserContext.setKullanici(kullanici);
        }

        return kullanici;
    }

    @Override
    public List<Kullanici> roleAitKullaniciList(UUID rolId) {
        return userRepository.kullaniciListByRol(rolId);
    }

    @Override
    public List<Kullanici> completeAllKullanici(String query) {
        return userRepository.findByAdSoyadContainingIgnoreCase(query);
    }

    @Override
    public Collection<Kullanici> findKullanicilarByMusteriId(UUID musteriId) {
        return userRepository.findByHesapEtkinTrue();
    }

    @Override
    public List<Kullanici> findByKullaniciIdList(List<UUID> kullaniciIdList) {
        return userRepository.findByIdIn(kullaniciIdList);
    }

    @Override
    public void ldapKulaniciEslestir(String eposta, String ldapEposta) {
        Kullanici kullanici = fetchKullaniciByEposta(eposta);
        kullanici.ldapKullanicisiniGuncelle(ldapEposta);
        userRepository.save(kullanici);
    }



    @Override
    public Kullanici findOne(UUID id) {
        return userRepository.findOne(id);
    }

    @Override
    public void updateUserContext() {
        currentUserContext.setKullanici(fetchCurrentUser());
    }

    @Override
    public Kullanici fetchJobUser() {
        return userRepository.findOne(UUID.fromString(Kullanici.JOB_USER));
    }

    @Override
    public boolean checkValidIdAndToken(UUID kullaniciId, UUID token) {
        return tokenRepository.findByKullaniciAndTokenAndExpiryDateBetween(
            kullaniciId,
            token,
            LocalDateTime.now().minusHours(Token.EXPIRATION_TIME),
            LocalDateTime.now()
        ).isPresent();
    }
}
