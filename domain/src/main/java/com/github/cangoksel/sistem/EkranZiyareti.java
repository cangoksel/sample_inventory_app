package com.github.cangoksel.sistem;


import com.github.cangoksel.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by herdemir on 24/02/15.
 */
@Entity
@Table(name = "EKRAN_ZIYARETI")
@Getter
@Slf4j
public class EkranZiyareti extends AbstractEntity {
    @Column(name = "ZIYARET_ZAMANI")
    private LocalDateTime ziyaretZamani;
    @Column(name = "VIEW_CLASS")
    private String viewClass;
    @Column(name = "ZIYARET_EDEN_KULLANICI")
    private String ziyaretEdenKullanici;

    protected EkranZiyareti() {
    }

    public EkranZiyareti(LocalDateTime ziyaretZamani, String viewClass, String ziyaretEdenKullanici) {
        this.ziyaretZamani = ziyaretZamani;
        this.viewClass = viewClass;
        this.ziyaretEdenKullanici = ziyaretEdenKullanici;
    }

    public LocalDateTime getZiyaretZamani() {
        return ziyaretZamani;
    }

    public String getViewClass() {
        return viewClass;
    }

    public String getZiyaretEdenKullanici() {
        return ziyaretEdenKullanici;
    }
}
