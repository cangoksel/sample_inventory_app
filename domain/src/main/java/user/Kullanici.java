package user;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Email;
import org.springframework.util.Assert;
import tr.com.innova.sample.common.entity.AbstractVersionedEntity;
import tr.com.innova.sample.common.exceptions.DomainException;
import tr.com.innova.sample.common.utils.LocaleUtils;
import tr.com.innova.sample.common.utils.PasswordUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by herdemir on 02.02.2015.
 */
@Entity
@Table(name = "KULLANICI", uniqueConstraints = {@UniqueConstraint(columnNames = "EPOSTA", name = "UK_KULLANICI_EPOSTA")})
@Audited
@Cacheable
public class Kullanici extends AbstractVersionedEntity implements KullaniciInfo {
    public static final String ESKI_SIFRE_HATALI = "Eski Şifre hatalı.";
    public static final String ESKI_SIFRE_ILE_YENI_SIFRE_AYNI_OLAMAZ = "Eski Şifre ile Yeni Şifre aynı olamaz.";
    public static final String EN_AZ_BIR_ROL_SECIMI_YAPILMALIDIR = "En az bir rol seçimi yapılmalıdır.";
    public static final String JOB_USER = "ffffffff-ffff-ffff-ffff-ffffffffffff";

    @Email
    @Column(name = "EPOSTA")
    private String eposta;
    @Column(name = "AD")
    private String ad;
    @Column(name = "SOYAD")
    private String soyad;

    @Column(name = "SIFRE", nullable = false)
    private String sifre;

    @Column(name = "USER_NAME")
    private String userName;

    @NotAudited
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @NotAudited
    @Column(name = "ILK_KAYIT_TARIHI")
    private LocalDateTime ilkKayitTarihi;


    @Column(name = "HESAP_ETKIN")
    private Boolean hesapEtkin;
    @Column(name = "HESAP_KILITLI")
    private Boolean hesapKilitli;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "KULLANICI_ROL",
        joinColumns = {@JoinColumn(name = "KULLANICI_ID", foreignKey = @ForeignKey(name = "FK_KULLANICI_ROL_KULLANICI"))},
        inverseJoinColumns = {@JoinColumn(name = "ROL_ID", foreignKey = @ForeignKey(name = "FK_KULLANICI_ROL_ROL"))},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"KULLANICI_ID", "ROL_ID"})}
    )
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<Rol> roller = new HashSet<>();

    @Email
    @Column(name = "LDAP_EPOSTA")
    private String ldapEposta;

    protected Kullanici() {
    }

    public Kullanici(String eposta) {
        this.eposta = eposta;
    }

    public Kullanici(String eposta, Boolean hesapEtkin) {
        this.eposta = eposta;
        this.hesapEtkin = hesapEtkin;
    }



    public static Collection<Kullanici> autoComplete(String query, Collection<Kullanici> kullaniciList) {
        if (Strings.isNullOrEmpty(query)) {
            return kullaniciList;
        }
        final String q = query.toUpperCase(LocaleUtils.TURKISH);
        return kullaniciList.stream()
                            .filter(f -> f.getAdiSoyadi().toUpperCase(LocaleUtils.TURKISH).contains(q))
                            .collect(Collectors.toList());
    }

    @Override
    public String getAd() {
        return ad;
    }

    @Override
    public String getSoyad() {
        return soyad;
    }

    @Override
    public String getEposta() {
        return eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public Set<Rol> getRoller() {
        return ImmutableSet.copyOf(roller);
    }

    public String getLdapEposta() {
        return ldapEposta;
    }

    @Override
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    @Override
    public LocalDateTime getIlkKayitTarihi() {
        return ilkKayitTarihi;
    }

    public void ldapKullanicisiniGuncelle(String ldapEposta) {
        this.ldapEposta = ldapEposta;
    }

    public void sifreDegistir(final String eskiSifre, final String yeniSifre) {
        Assert.notNull(eskiSifre,"Eski şifre boş olamaz.");
        Assert.notNull(yeniSifre,"Yeni şifre boş olamaz.");

        if (!PasswordUtils.isMatch(eskiSifre, this.getSifre())) {
            throw new DomainException(ESKI_SIFRE_HATALI);
        }

        if (yeniSifre.equals(eskiSifre)) {
            throw new DomainException(ESKI_SIFRE_ILE_YENI_SIFRE_AYNI_OLAMAZ);
        }

        this.sifreOlustur(yeniSifre);
    }

    public void sifreOlustur(final String yeniSifre) {
        Assert.notNull(yeniSifre,"Yeni şifre boş olamaz.");

        this.sifre = PasswordUtils.encode(yeniSifre);
    }

    public void updateSuccesfulLogin() {
        lastLogin = LocalDateTime.now();
    }

    public void hesapKapat() {
        this.roller.clear();
        hesapEtkin = Boolean.FALSE;
        hesapKilitli = Boolean.FALSE;
    }

    public void hesapYenidenAc(String ad, String soyad, Collection<Rol> roller) {
        Assert.notNull(ad,"Ad boş olamaz.");
        Assert.notNull(soyad,"Soyad boş olamaz.");
        Assert.notEmpty(roller,"Roller boş olamaz.");
        this.ad = ad;
        this.soyad = soyad;
        this.hesapKilitli = Boolean.FALSE;
        this.roller.addAll(roller);
        this.hesapEtkin = Boolean.TRUE;
    }

    public void hesabKilitle() {
        hesapKilitli = Boolean.TRUE;
    }

    public void hesapKilitKaldir() {
        hesapKilitli = Boolean.FALSE;
    }

    @Override
    public boolean isHesapEtkin() {
        return hesapEtkin != null && hesapEtkin;
    }

    @Override
    public boolean isHesapKilitli() {
        return hesapKilitli == null || hesapKilitli;
    }

    public String getUserName() {
        return userName;
    }

    public void guncelle(String eposta, String ad, String soyad, Boolean aktifMi, String username,
        Collection<Rol> roller) {
        Assert.notNull(aktifMi,"Aktif boş olamaz.");
        Assert.notNull(roller,"Roller boş olamaz.");
        Assert.hasText(eposta,"Eposta boş olamaz.");
        checkRoller(roller);

        this.eposta = eposta;
        adSoyadGuncelle(ad, soyad);
        this.userName = username;
        this.hesapKilitli = !aktifMi;
        this.roller = Sets.newHashSet(roller);
    }

    private void checkRoller(final Collection<Rol> roller) {
        if (roller == null || roller.isEmpty()) {
            throw new DomainException(EN_AZ_BIR_ROL_SECIMI_YAPILMALIDIR);
        }
    }

    public void adSoyadGuncelle(final String ad, final String soyad) {
        Assert.hasText(ad,"Ad boş olamaz.");
        Assert.hasText(soyad,"Soyad boş olamaz.");
        this.ad = ad;
        this.soyad = soyad;
    }

    @Override
    public String getAdiSoyadi() {
        return ad + (soyad == null ? "" : " " + soyad);
    }


    @Override
    public boolean isSistemAdmin() {
        return roller.stream().anyMatch(f -> f.getKod().equals(Rol.ROLE_SISTEM_ADMIN));
    }

    @Override
    public boolean isSistemAdminOrDestekUser() {
        final Predicate<Rol> sistemAdmin = f -> f.getKod().equals(Rol.ROLE_SISTEM_ADMIN);
        final Predicate<Rol> destekUser = f -> f.getKod().equals(Rol.ROLE_DESTEK);

        return roller.stream()
                     .anyMatch(sistemAdmin.or(destekUser));
    }


    public static class Builder {
        private final Kullanici kullanici;

        public Builder() {
            kullanici = new Kullanici();
            kullanici.hesapEtkin = Boolean.TRUE;
            kullanici.hesapKilitli = Boolean.FALSE;
            kullanici.ilkKayitTarihi = LocalDateTime.now();
        }

        public Kullanici build() {
            return kullanici;
        }

        public Builder ad(String ad) {
            Assert.notNull(ad,"Ad boş olamaz.");
            kullanici.ad = ad;
            return this;
        }

        public Builder soyad(String soyad) {
            Assert.notNull(soyad,"Soyad boş olamaz.");
            kullanici.soyad = soyad;
            return this;
        }

        public Builder eposta(String eposta) {
            Assert.notNull(eposta,"Eposta boş olamaz.");
            kullanici.eposta = eposta;
            return this;
        }

        public Builder sifre(String sifre) {
            Assert.notNull(sifre,"Şifre boş olamaz.");
            kullanici.sifreOlustur(sifre);
            return this;
        }

        public Builder addRol(Rol rol) {
            Assert.notNull(rol,"Rol boş olamaz.");
            kullanici.roller.add(rol);
            return this;
        }

        public Builder addRol(Collection<Rol> roller) {
            Assert.notNull(roller,"Roller boş olamaz.");
            kullanici.roller.addAll(roller);
            return this;
        }

        public Builder username(String username) {
            kullanici.userName = username;
            return this;
        }
    }
}
