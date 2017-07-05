package com.github.cangoksel.user.model;


import com.github.cangoksel.user.Kullanici;
import com.github.cangoksel.user.Yetki;

import java.util.List;

/**
 * Created by usuicmez on 14.04.2015.
 */
public class RolViewModel {

    private String sorguKriteriRolAdi;

    private List<RolSorguSonucuViewModel> rolSorguSonucuViewModelList;

    private List<RolSorguSonucuViewModel> filteredRolSorguSonucuViewModelList;

    private RolSorguSonucuViewModel secilenRolSorguSonucuViewModel;

    private RolSorguSonucuViewModel guncellenenRolSorguSonucuViewModel;

    private List<Yetki> allYetkiList;

    private boolean rolGuncellemeEtkin;

    private Kullanici kullanici;

    public String getSorguKriteriRolAdi() {
        return sorguKriteriRolAdi;
    }

    public void setSorguKriteriRolAdi(String sorguKriteriRolAdi) {
        this.sorguKriteriRolAdi = sorguKriteriRolAdi;
    }

    public void kriterleriTemizle() {
        sorguKriteriRolAdi = null;
    }

    public List<RolSorguSonucuViewModel> getRolSorguSonucuViewModelList() {
        return rolSorguSonucuViewModelList;
    }

    public void setRolSorguSonucuViewModelList(
        List<RolSorguSonucuViewModel> rolSorguSonucuViewModelList) {
        this.rolSorguSonucuViewModelList = rolSorguSonucuViewModelList;
    }

    public List<RolSorguSonucuViewModel> getFilteredRolSorguSonucuViewModelList() {
        return filteredRolSorguSonucuViewModelList;
    }

    public void setFilteredRolSorguSonucuViewModelList(
        List<RolSorguSonucuViewModel> filteredRolSorguSonucuViewModelList) {
        this.filteredRolSorguSonucuViewModelList = filteredRolSorguSonucuViewModelList;
    }

    public RolSorguSonucuViewModel getSecilenRolSorguSonucuViewModel() {
        return secilenRolSorguSonucuViewModel;
    }

    public void setSecilenRolSorguSonucuViewModel(
        RolSorguSonucuViewModel secilenRolSorguSonucuViewModel) {
        this.secilenRolSorguSonucuViewModel = secilenRolSorguSonucuViewModel;
    }

    public List<Yetki> getAllYetkiList() {
        return allYetkiList;
    }

    public void setAllYetkiList(List<Yetki> allYetkiList) {
        this.allYetkiList = allYetkiList;
    }

    public boolean isRolGuncellemeEtkin() {
        return rolGuncellemeEtkin;
    }

    public void setRolGuncellemeEtkin(boolean rolGuncellemeEtkin) {
        this.rolGuncellemeEtkin = rolGuncellemeEtkin;
    }

    public RolSorguSonucuViewModel getGuncellenenRolSorguSonucuViewModel() {
        return guncellenenRolSorguSonucuViewModel;
    }

    public void setGuncellenenRolSorguSonucuViewModel(
        RolSorguSonucuViewModel guncellenenRolSorguSonucuViewModel) {
        this.guncellenenRolSorguSonucuViewModel = guncellenenRolSorguSonucuViewModel;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public boolean isRolGuncellenebilir()
    {
        return  !guncellenenRolSorguSonucuViewModel.isSistemRolu();
    }

}
