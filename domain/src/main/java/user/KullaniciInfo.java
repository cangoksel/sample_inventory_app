package user;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by herdemir on 16.10.2015.
 */
public interface KullaniciInfo {
    UUID getId();

    String getAd();

    String getSoyad();

    String getEposta();

    LocalDateTime getLastLogin();

    LocalDateTime getIlkKayitTarihi();

    boolean isHesapEtkin();

    boolean isHesapKilitli();

    String getAdiSoyadi();

    boolean isSistemAdmin();

    boolean isSistemAdminOrDestekUser();


}
