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
public class IsbirligiFirma extends AbstractVersionedEntity{

    @Column
    private String firmaAdi;

    @Column
    private String isbirligiKonsu;
}
