package user;


import tr.com.innova.sample.user.model.RolSorguSonucuViewModel;
import tr.com.innova.sample.user.model.RolYeniKayitViewModel;
import tr.com.innova.sample.user.query.RolQuery;

import java.util.List;
import java.util.UUID;

/**
 * Created by gcan on 04.02.2015.
 */
public interface RolService {
    List<Rol> tumRoller();

    Rol fetchRolunYetkileri(Rol rol);

    void rolKaydet(Rol rol);

    List<RolSorguSonucuViewModel> sorgula(RolQuery query);

    Rol getRolByKod(String kod);

    Rol fetchRolYetki(final RolSorguSonucuViewModel rolSorguSonucuViewModel);

    boolean rolAdiMevcutMu(UUID id, String aciklama);

    void guncelle(final RolSorguSonucuViewModel rolSorguSonucuViewModel);

    void farkliKaydet(final RolSorguSonucuViewModel rolSorguSonucuViewModel);

    void kaydet(final RolYeniKayitViewModel rolYeniKayitViewModel);

    void sil(final RolSorguSonucuViewModel rolSorguSonucuViewModel);




}
