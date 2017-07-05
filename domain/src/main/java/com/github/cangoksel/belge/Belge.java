package com.github.cangoksel.belge;

import com.github.cangoksel.common.entity.AbstractEntity;
import com.google.common.base.Charsets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by herdemir on 16.06.2015.
 */
@Getter
@Entity
@Table(name = "BELGE")
@Audited
@Builder
@AllArgsConstructor
public class Belge extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "BELGE_TIPI")
    private BelgeTipi belgeTipi;

    @Column(name = "BELGE_ADI")
    private String belgeAdi;

    @Column(name = "KHY_ID")
    private String khyId;

    @Column(name = "BELGE_BOYUTU")
    private long belgeBoyutu;

    @Column(name = "OLUSTURMA_ZAMANI")
    private LocalDateTime olusturmaZamani = LocalDateTime.now();

    @Column(name = "SILINEBILIR_MI")
    private boolean silinebilirMi = true;

    @Column(name = "ACIKLAMA")
    private String aciklama;

    protected Belge() {
    }

    public Belge(final BelgeTipi belgeTipi, final String belgeAdi, final long belgeBoyutu, final String aciklama) {
        this.belgeTipi = belgeTipi;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        this.aciklama = aciklama;
    }

    public Belge(UUID id, final BelgeTipi belgeTipi, final String belgeAdi, final long belgeBoyutu, final String aciklama) {
        this.belgeTipi = belgeTipi;
        this.id = id;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        this.aciklama = aciklama;
    }

    public Belge(final String belgeAdi, final long belgeBoyutu) {
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
    }

    public Belge(BelgeTipi belgeTipi, String belgeAdi, int belgeBoyutu, boolean silinebilirMi) {
        this.belgeTipi = belgeTipi;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        this.silinebilirMi = silinebilirMi;
    }

    public Belge(BelgeTipi belgeTipi, String belgeAdi, int size, boolean silinebilirMi, String khyId) {
        this(belgeTipi,belgeAdi,size,silinebilirMi);
        this.khyId = khyId;
    }

    public static String getBelgeAdiString(String belgeAdi) {
        try {
            return new String(belgeAdi.getBytes("ISO-8859-9"), Charsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            return belgeAdi;
        }
    }

    public String getBelgeAdiString() {
        return getBelgeAdiString(belgeAdi);
    }

    public String getBelgeOrijinalAdi() {
        return belgeAdi;
    }

    public void updateBelgeTipi(final BelgeTipi belgeTipi) {
        this.belgeTipi = belgeTipi;
    }

    public void updateAciklama(final String aciklama) {
        this.aciklama = aciklama;
    }

    public void undelete() {
        this.deleted = false;
    }


}
