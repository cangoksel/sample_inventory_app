package user.model;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import tr.com.innova.sample.common.validation.constraints.Password;
import tr.com.innova.sample.user.Kullanici;
import tr.com.innova.sample.user.Rol;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tozyurek on 14.04.2015.
 */
@Setter
@Getter
public class KullaniciViewModel {
    private UUID id;
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String ad;
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String soyad;
    @Size(max = 255)
    private String username;
    @NotBlank
    @NotNull
    @Email
    @Size(max = 255)
    private String eposta;
    @NotBlank
    @NotNull
    @Password
    private String sifre;
    @NotBlank
    @NotNull
    @Password
    private String sifreTekrar;

    private Set<Rol> rolList;

    private List<Rol> guncellemeRolList;
    @NotNull
    private Boolean aktifMi;
    private LocalDateTime tanitimZamani;

    @Setter(AccessLevel.NONE)
    private boolean tanimlandi;

    @Setter(AccessLevel.NONE)
    private String orjinalEposta;

    public KullaniciViewModel() {
    }

    public KullaniciViewModel(Kullanici kullanici) {
        this.id = kullanici.getId();
        this.ad = kullanici.getAd();
        this.soyad = kullanici.getSoyad();
        this.username = kullanici.getUserName();
        this.eposta = kullanici.getEposta();
        this.orjinalEposta = kullanici.getEposta();
        this.rolList = Sets.newHashSet(kullanici.getRoller());
        this.guncellemeRolList = Lists.newArrayList(kullanici.getRoller());
        this.aktifMi = !kullanici.isHesapKilitli();
        this.tanitimZamani = kullanici.getIlkKayitTarihi();
    }

    public String getKullaniciAdSoyad() {
        return ad + " " + soyad;
    }

    public String getRolListString() {
        if (rolList == null) {
            return null;
        }
        return rolList.stream()
                      .map(Rol::getAciklama)
                      .collect(Collectors.joining(", "));
    }

    public String getGuncellemeRolListString() {
        if (guncellemeRolList == null) {
            return null;
        }
        return guncellemeRolList.stream()
                                .map(Rol::getAciklama)
                                .collect(Collectors.joining(", "));
    }

    public String getAktifMiString() {
        return aktifMi ? "Aktif" : "Kilitli";
    }



    public String getTanitimZamani() {
        return tanitimZamani == null ? null : tanitimZamani.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public void tanimlandi() {
        this.tanimlandi = true;
    }

    public Kullanici map() {
        return new Kullanici.Builder()
            .ad(getAd())
            .soyad(getSoyad())
            .eposta(getEposta())
            .username(getUsername())
            .sifre(getSifre())
            .addRol(getRolList())
            .build();
    }

    public boolean isEpostaChanged() {
        return !Strings.nullToEmpty(orjinalEposta).equals(eposta);
    }
}
