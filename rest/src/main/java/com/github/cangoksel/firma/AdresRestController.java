package com.github.cangoksel.firma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/adres")
public class AdresRestController {

    @Autowired
    private AdresService adresService;

    @GetMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Adres>> getAll() {
        return ResponseEntity.ok(adresService.getAllAdres());
    }

    @GetMapping(value = "/{adresId}",produces = "application/json;charset=UTF-8")
    public Adres getOne(@PathVariable UUID adresId) {
        return adresService.findById(adresId);
    }
}
