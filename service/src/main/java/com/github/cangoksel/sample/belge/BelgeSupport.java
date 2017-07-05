package com.github.cangoksel.sample.belge;

import com.github.cangoksel.belge.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by herdemir on 11.10.2016.
 */
@Component
public class BelgeSupport {
    private final BelgeService belgeService;
    private final BelgeRepository belgeRepository;

    @Autowired
    public BelgeSupport(BelgeService belgeService, BelgeRepository belgeRepository) {
        this.belgeService = belgeService;
        this.belgeRepository = belgeRepository;
    }

    public Belge belgeEkle(BelgeModel belgeModel) {
        return belgeService.belgeEkle(belgeModel);
    }

    public void updateBelge(final BelgeViewModel model) {
        final UUID belgeId = model.getId();
        final Belge belge = belgeRepository.findOne(belgeId);

        belge.updateBelgeTipi(model.getBelgeTipi());
        belge.updateAciklama(model.getAciklama());

        belgeRepository.save(belge);
    }

    public void belgeSilGeriAl(UUID belgeId) {
        final Belge belge = belgeRepository.findOne(belgeId);

        if (belge.isDeleted()) {
            belge.undelete();
        } else {
            belge.delete();
        }

        belgeRepository.save(belge);
    }
}
