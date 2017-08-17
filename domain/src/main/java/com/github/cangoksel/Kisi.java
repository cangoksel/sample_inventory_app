package com.github.cangoksel;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Kisi extends AbstractVersionedEntity{

    @Column
    private String adi;
}
