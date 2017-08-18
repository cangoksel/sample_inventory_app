package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Getter
@Slf4j
public class Adres extends AbstractVersionedEntity {
    @Column
    private String acikAdres;
    @Column
    @Enumerated(EnumType.STRING)
    private Il il;
    @Column
    @Enumerated(EnumType.STRING)
    private Ulke ulke;
}
