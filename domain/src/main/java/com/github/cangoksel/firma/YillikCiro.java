package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YillikCiro extends AbstractVersionedEntity {

    @Column
    private LocalDate yil;

    @Columns(columns = {@Column(name = "CIRO_TUTAR"), @Column(name = "CIRO_PB", length = 3)})
    private Money ciro;

}
