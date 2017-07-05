package user.model;

import org.hibernate.validator.constraints.NotBlank;
import tr.com.innova.sample.user.Kullanici;
import tr.com.innova.sample.user.Rol;
import tr.com.innova.sample.user.RolTipi;
import tr.com.innova.sample.user.Yetki;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuicmez on 17.04.2015.
 */
public class RolYeniKayitViewModel {
    private String kod;
    private Boolean isSistemRolu;
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String aciklama;
    private RolTipi rolTipi;
    private List<Yetki> yetkiler = new ArrayList<>();
    private Rol secilenRol;

    private List<Yetki> allYetkiList;
    private List<Rol> allRolList;

    public void initialize(Kullanici kullanici, List<Yetki> allYetkiList) {
        rolTipi = RolTipi.findRolTipi(kullanici);
        kod = Rol.randomRolKoduOlustur(rolTipi);
        isSistemRolu = false;
        this.allYetkiList = allYetkiList;
    }

    public void temizle() {
        aciklama = null;
        yetkiler.clear();
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public RolTipi getRolTipi() {
        return rolTipi;
    }

    public List<Yetki> getYetkiler() {
        return yetkiler;
    }

    public void setYetkiler(List<Yetki> yetkiler) {
        this.yetkiler = yetkiler;
    }

    public List<Yetki> getAllYetkiList() {
        return allYetkiList;
    }

    public Boolean isSistemRolu() {
        return isSistemRolu;
    }


    public String getKod() {
        return kod;
    }

    public List<Rol> getAllRolList() {
        return allRolList;
    }

    public void setAllRolList(List<Rol> allRolList) {
        this.allRolList = allRolList;
    }

    public Rol getSecilenRol() {
        return secilenRol;
    }

    public void setSecilenRol(Rol secilenRol) {
        this.secilenRol = secilenRol;
    }
}
