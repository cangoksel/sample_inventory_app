package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalisanSayiBilgisi extends AbstractVersionedEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private CalisanTipi calisanTipi;

    @Column
    private Long kisiSayisi;
}
