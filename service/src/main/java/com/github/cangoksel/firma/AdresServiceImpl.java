package com.github.cangoksel.firma;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class AdresServiceImpl implements AdresService {


    @Autowired
    private AdresRepository adresRepository;

    @Override
    public Adres findById(UUID id) {
        return adresRepository.findOne(id);
    }

    @Override
    public void save(Adres adres) {
        adresRepository.save(adres);
    }

    @Override
    public List<Adres> getAllAdres() {
        return adresRepository.findAll();
    }
}
