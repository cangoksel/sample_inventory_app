package belge;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by herdemir on 16.06.2015.
 */
@Setter
@Getter
public class BelgeViewModel implements Comparable<BelgeViewModel> {
    private final LocalDateTime belgeTarihi;
    @Setter(AccessLevel.NONE)
    private UUID id;
    @NotNull
    private BelgeTipi belgeTipi;
    @Size(max = 250)
    private String belgeAdi;
    private long belgeBoyutu;
    @Setter(AccessLevel.NONE)
    private boolean silinebilirMi = true;
    private String aciklama;

    @Setter(AccessLevel.NONE)
    private UUID referansId;

    @Setter(AccessLevel.NONE)
    private boolean deleted;

    public BelgeViewModel() {
        belgeTarihi = LocalDateTime.now();
    }

    public BelgeViewModel(BelgeTipi belgeTipi, String belgeAdi, long belgeBoyutu, String aciklama) {
        this.belgeTipi = belgeTipi;
        this.belgeAdi = belgeAdi;
        this.belgeBoyutu = belgeBoyutu;
        belgeTarihi = LocalDateTime.now();
        this.aciklama = aciklama;
    }

    public BelgeViewModel(final Belge belge) {
        id = belge.getId();
        belgeTipi = belge.getBelgeTipi();
        belgeAdi = belge.getBelgeAdi();
        belgeBoyutu = belge.getBelgeBoyutu();
        belgeTarihi = belge.getOlusturmaZamani();
        silinebilirMi = belge.isSilinebilirMi();
        aciklama = belge.getAciklama();
        deleted = belge.isDeleted();
    }

    public BelgeViewModel(final Belge belge, final UUID referansId) {
        this(belge);
        this.referansId = referansId;
    }

    public Belge map() {
        if (id != null) {
            return new Belge(id, belgeTipi, belgeAdi, belgeBoyutu, aciklama);
        }
        return new Belge(belgeTipi, belgeAdi, belgeBoyutu, aciklama);
    }

    public boolean isYeni() {
        return id == null;
    }

    public String getBelgeAdiString() {
        return Belge.getBelgeAdiString(belgeAdi);
    }

    @Override
    public int compareTo(BelgeViewModel o) {
        int result = 0;
        if (this.belgeTarihi != null && o.belgeTarihi != null) {
            result = o.belgeTarihi.compareTo(this.belgeTarihi);
        }
        if (result == 0) {
            if (this.id != null && o.id != null) {
                result = this.id.compareTo(o.id);
            }
        }
        if (result == 0) {
            if (this.belgeAdi != null && o.belgeAdi != null) {
                result = this.belgeAdi.compareTo(o.belgeAdi);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
