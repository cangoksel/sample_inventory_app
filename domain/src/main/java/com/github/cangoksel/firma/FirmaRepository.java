package com.github.cangoksel.firma;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirmaRepository extends JpaRepository<Firma, UUID> {

}
