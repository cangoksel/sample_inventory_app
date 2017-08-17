package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import com.github.cangoksel.il.Ulke;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ihracat extends AbstractVersionedEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private Ulke yapilanUlke;
    @Columns(columns = {@Column(name = "TUTAR"), @Column(name = "TUTAR_PB", length = 3)})
    private Money tutar;
    @Column
    private Long miktar;
}
