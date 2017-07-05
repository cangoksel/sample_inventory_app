package com.github.cangoksel.belge;

import com.google.common.io.ByteSource;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by herdemir on 21.12.2015.
 */
@Getter
public class BelgeModel implements Serializable, Comparable<BelgeModel> {
    private Path belgeContent;
    private byte[] icerik;
    private final UUID belgeId;
    private final String belgeAdi;
    private final long belgeBoyutu;
    private final BelgeViewModel viewModel;
    private final LocalDateTime belgeTarihi;
    @Setter
    private String aciklama;
    @Setter
    private BelgeTipi belgeTipi;
    private boolean silinebilirMi = true;
    private UUID referansId;
    private boolean deleted;

    public BelgeModel(final BelgeViewModel viewModel) {
        belgeId = viewModel.getId();
        belgeAdi = viewModel.getBelgeAdi();
        belgeTipi = viewModel.getBelgeTipi();
        belgeBoyutu = viewModel.getBelgeBoyutu();
        belgeContent = null;
        aciklama = viewModel.getAciklama();
        silinebilirMi = viewModel.isSilinebilirMi();
        this.viewModel = viewModel;
        this.belgeTarihi = viewModel.getBelgeTarihi();
        this.referansId = viewModel.getReferansId();
        deleted = viewModel.isDeleted();
    }

    public BelgeModel(final Path belgeContent, final String belgeAdi, final long belgeBoyutu, final BelgeTipi belgeTipi, final String aciklama) {
        this.belgeContent = belgeContent;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        this.belgeTipi = belgeTipi;
        this.belgeTarihi = LocalDateTime.now();
        this.belgeId = null;
        this.viewModel = null;
        this.aciklama = aciklama;
    }

    public BelgeModel(final byte[] icerik, final String belgeAdi, final long belgeBoyutu, final BelgeTipi belgeTipi, final String aciklama) {
        this.icerik = icerik;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        this.belgeTipi = belgeTipi;
        this.belgeTarihi = LocalDateTime.now();
        this.belgeId = null;
        this.viewModel = null;
        this.aciklama = aciklama;
    }

    public Belge map() {
        if (belgeId != null) {
            return new Belge(belgeId, belgeTipi, belgeAdi, belgeBoyutu, aciklama);
        }
        return new Belge(belgeTipi, belgeAdi, belgeBoyutu, aciklama);
    }

    public InputStream getUploadedFileStream() throws IOException {
        if (belgeContent != null) {
            return Files.newInputStream(belgeContent, StandardOpenOption.DELETE_ON_CLOSE);
        }
        if (icerik != null&& icerik.length != 0 ) {
            return ByteSource.wrap(icerik).openStream();
        } else {
            return null;
        }
    }

    public String getBelgeAdiString() {
        return Belge.getBelgeAdiString(belgeAdi);
    }

    public boolean isYeni() {
        return belgeId == null;
    }

    public BelgeViewModel getViewModel() {
        viewModel.setBelgeTipi(belgeTipi);
        viewModel.setAciklama(aciklama);
        return viewModel;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(BelgeModel o) {
        int result = 0;
        if (this.belgeTarihi != null && o.belgeTarihi != null) {
            result = o.belgeTarihi.compareTo(this.belgeTarihi);
        }
        if (result == 0) {
            if (this.belgeId != null && o.belgeId != null) {
                result = this.belgeId.compareTo(o.belgeId);
            }
        }
        if (result == 0) {
            if (this.belgeAdi != null && o.belgeAdi != null) {
                result = this.belgeAdi.compareTo(o.belgeAdi);
            }
        }
        return result;
    }
}
