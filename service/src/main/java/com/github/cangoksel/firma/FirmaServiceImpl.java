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
public class FirmaServiceImpl implements FirmaService {

    @Autowired
    private FirmaRepository firmaRepository;

    @Override
    public Firma findById(UUID id) {
        return firmaRepository.findOne(id);
    }

    @Override
    public void save(Firma firma) {
        firmaRepository.save(firma);
    }

    @Override
    public List<Firma> getAllFirma() {
        return firmaRepository.findAll();
    }
}
