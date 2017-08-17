package com.github.cangoksel.firma;

import java.util.List;
import java.util.UUID;

public interface FirmaService {
    Firma findById(UUID id);

    void save(Firma firma);

    List<Firma> getAllFirma();
}
