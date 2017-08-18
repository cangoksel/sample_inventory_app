package com.github.cangoksel.firma;

import java.util.List;
import java.util.UUID;

public interface AdresService {
    Adres findById(UUID id);

    void save(Adres adres);

    List<Adres> getAllAdres();
}
