package user.model;

import org.hibernate.validator.constraints.NotBlank;
import tr.com.innova.sample.user.Kullanici;
import tr.com.innova.sample.user.Rol;
import tr.com.innova.sample.user.RolTipi;
import tr.com.innova.sample.user.Yetki;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by usuicmez on 14.04.2015.
 */
public class RolSorguSonucuViewModel {
    private UUID id;
    @Size(max = 255)
    private String kod;
    private boolean sistemRolu;
    @NotBlank
    @NotNull
    @Size(max = 255)
    private String aciklama;
    private List<Yetki> yetkiler;
    private RolTipi rolTipi;
    private boolean rolAdiMevcut;

    public RolSorguSonucuViewModel() {}

    public RolSorguSonucuViewModel(Rol rol) {
        this.id = rol.getId();
        this.kod = rol.getKod();
        this.sistemRolu = rol.isSistemRolu();
        this.aciklama = rol.getAciklama();
        this.rolTipi = rol.getRolTipi();
    }

    public RolSorguSonucuViewModel(RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        this.id = rolSorguSonucuViewModel.getId();
        this.kod = rolSorguSonucuViewModel.getKod();
        this.sistemRolu = rolSorguSonucuViewModel.isSistemRolu();
        this.aciklama = rolSorguSonucuViewModel.getAciklama();
        this.rolTipi = rolSorguSonucuViewModel.getRolTipi();
    }

    public void update(RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        this.aciklama = rolSorguSonucuViewModel.getAciklama();
    }

    public String getKod() {
        return kod;
    }

    public boolean isSistemRolu() {
        return sistemRolu;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public List<Yetki> getYetkiler() {
        return yetkiler;
    }

    public UUID getId() {
        return id;
    }

    public RolTipi getRolTipi() {
        return rolTipi;
    }

    public void setYetkiler(List<Yetki> yetkiler) {
        this.yetkiler = yetkiler;
    }

    public boolean isRolAdiMevcut() {
        return rolAdiMevcut;
    }

    public void setRolAdiMevcut(boolean rolAdiMevcut) {
        this.rolAdiMevcut = rolAdiMevcut;
    }


    public void farkliKaydetIcinHazirla(Kullanici kullanici) {
        this.id = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RolSorguSonucuViewModel that = (RolSorguSonucuViewModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
