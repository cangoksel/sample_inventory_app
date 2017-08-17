package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrunGrubuKodu extends AbstractVersionedEntity{

    @Column
    private String urunGrubuKoduAdi;

    @Column
    private String urunGrubuKodu;
}
