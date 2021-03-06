package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Columns;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class Ihracat extends AbstractVersionedEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private Ulke yapilanUlke;
    @Columns(columns = {@Column(name = "TUTAR"), @Column(name = "TUTAR_PB", length = 3)})
    private Money tutar;
    @Column
    private Long miktar;
    @Column
    private LocalDate yil;
}
