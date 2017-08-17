package com.github.cangoksel;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FaaliyetKodu extends AbstractVersionedEntity {

    @Column
    private  String alanAdi;
}
