package com.github.cangoksel.belge;

import lombok.Getter;


/**
 * Created by herdemir on 11.10.2016.
 */
public enum BelgeServisTipi {
    DOSYA(Belge.class),
    KLASOR(Belge.class);

    @Getter
    private final Class serviceClass;

    BelgeServisTipi(final Class serviceClass) {
        this.serviceClass = serviceClass;
    }
}
