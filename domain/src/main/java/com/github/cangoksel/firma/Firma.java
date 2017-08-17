package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Firma extends AbstractVersionedEntity {


    private boolean firmaYetkinlikCalismasiYepildiMi;

    private GenelFirmaBilgileri genelBilgiler;

    private IsyeriBilgileri isyeriBilgileri;

    private OrtaklikBilgileri ortaklikBilgileri;

    private Set<ProjeBilgisi> projeBilgileri;

    private FinansalBilgileri finansalBilgileri;

    private Set<TesisBilgisi> tesisBilgisileri;

    private Set<UretimBilgisi> uretimBilgileri;
}
