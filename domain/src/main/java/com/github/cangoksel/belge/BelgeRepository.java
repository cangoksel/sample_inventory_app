package com.github.cangoksel.belge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by herdemir on 16.06.2015.
 */
public interface BelgeRepository extends JpaRepository<Belge, UUID> {
}
