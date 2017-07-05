package common.money;

import org.hibernate.envers.Audited;
import tr.com.innova.sample.common.entity.AbstractVersionedEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by herdemir on 14.07.2015.
 */
@Entity
@Table(name = "KUR",uniqueConstraints = @UniqueConstraint(columnNames = {"PARA_BIRIMI", "KUR_TARIHI"}))
@Cacheable
@Audited
public class Kur extends AbstractVersionedEntity {
    @Column(name = "ISLEM_TARIHI")
    private LocalDate islemTarihi;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARA_BIRIMI")
    private ParaBirimi paraBirimi;

    @Column(name = "KUR_TARIHI")
    private LocalDate kurTarihi;

    @Column(name = "KUR_ORANI", precision = 19, scale = 5)
    private BigDecimal kurOrani;

    @Column(name = "KUR_BIRIMI")
    private Integer kurBirimi;

    protected Kur() {
    }

    public Kur(LocalDate islemTarihi, ParaBirimi paraBirimi, LocalDate kurTarihi, BigDecimal kurOrani,
               Integer kurBirimi) {
        this.islemTarihi = islemTarihi;
        this.paraBirimi = paraBirimi;
        this.kurTarihi = kurTarihi;
        this.kurOrani = kurOrani;
        this.kurBirimi = kurBirimi;
    }

    public LocalDate getIslemTarihi() {
        return islemTarihi;
    }

    public ParaBirimi getParaBirimi() {
        return paraBirimi;
    }

    public LocalDate getKurTarihi() {
        return kurTarihi;
    }

    public BigDecimal getKurOrani() {
        return kurOrani;
    }

    public Integer getKurBirimi() {
        return kurBirimi;
    }

    public void kurBilgileriniGuncelle(BigDecimal kurOrani, Integer kurBirimi) {
        this.kurOrani = kurOrani;
        this.kurBirimi = kurBirimi;
    }
}
