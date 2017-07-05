package com.github.cangoksel.common.money;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by herdemir on 16.07.2015.
 */
public interface KurRepository extends JpaRepository<Kur, UUID> {
    Collection<Kur> findByKurTarihiOrderByParaBirimi(LocalDate kurTarihi);
}
