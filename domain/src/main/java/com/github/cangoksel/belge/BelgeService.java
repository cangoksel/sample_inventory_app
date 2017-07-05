package com.github.cangoksel.belge;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by herdemir on 14.12.2015.
 */
public interface BelgeService {
    Belge belgeEkle(BelgeModel belgeModel);

    List<Belge> belgeEkle(Collection<BelgeModel> belgeler);

    void belgeEkle(Belge belge, InputStream inputstream);

    void belgeSil(Belge belge);

    void belgeSil(Collection<Belge> belgeler);

    Belge belgeById(UUID id);

    Belge saveBelge(Belge belge);
}
