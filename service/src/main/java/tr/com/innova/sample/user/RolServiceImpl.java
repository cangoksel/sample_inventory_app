package tr.com.innova.sample.user;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.innova.sample.user.model.RolSorguSonucuViewModel;
import tr.com.innova.sample.user.model.RolYeniKayitViewModel;
import tr.com.innova.sample.user.query.RolQuery;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by gcan on 04.02.2015.
 */
@Service
@Transactional
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;

    public RolServiceImpl() {
    }

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> tumRoller() {
        return rolRepository.findAll();
    }



    @Override
    public Rol fetchRolunYetkileri(Rol rol) {
        return rolRepository.findById(rol.getId());
    }

    @Override
    public void rolKaydet(Rol rol) {
        rolRepository.save(rol);
    }



    @Override
    public List<RolSorguSonucuViewModel> sorgula(RolQuery query) {
        Set<Rol> all = rolRepository.findAll(query.getQuery());
        return all.stream().map(RolSorguSonucuViewModel::new).collect(Collectors.toList());
    }

    @Override
    public Rol getRolByKod(String kod) {
        return rolRepository.findByKod(kod).orElseGet(null);
    }

    @Override
    public Rol fetchRolYetki(final RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        return rolRepository.findById(rolSorguSonucuViewModel.getId());
    }

    @Override
    public boolean rolAdiMevcutMu(UUID id, String aciklama) {
        Optional<Rol> rol = rolRepository.findByAciklama(aciklama);
        if (rol.isPresent() && (!rol.get().getId().equals(id))) {
            return true;
        }
        return false;
    }

    @Override
    public void guncelle(final RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        Rol rol = rolRepository.findById(rolSorguSonucuViewModel.getId());
        rol.update(rolSorguSonucuViewModel.getAciklama(), rolSorguSonucuViewModel.getYetkiler());
        rolRepository.save(rol);
    }

    @Override
    public void farkliKaydet(final RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        Rol rol = new Rol(
            rolSorguSonucuViewModel.getRolTipi(),
            Rol.randomRolKoduOlustur(rolSorguSonucuViewModel.getRolTipi()),
            false,
            rolSorguSonucuViewModel.getAciklama(),
            rolSorguSonucuViewModel.getYetkiler()
        );
        rolRepository.save(rol);
    }


    @Override
    public void kaydet(final RolYeniKayitViewModel rolYeniKayitViewModel) {
        Rol rol = new Rol(
            rolYeniKayitViewModel.getRolTipi(),
            Rol.randomRolKoduOlustur(rolYeniKayitViewModel.getRolTipi()),
            false,
            rolYeniKayitViewModel.getAciklama(),
            rolYeniKayitViewModel.getYetkiler()
        );
        rolRepository.save(rol);
    }

    @Override
    public void sil(final RolSorguSonucuViewModel rolSorguSonucuViewModel) {
        Rol rol = rolRepository.findById(rolSorguSonucuViewModel.getId());
        rolRepository.delete(rol);
    }
}
