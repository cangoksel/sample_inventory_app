package com.github.cangoksel;

import com.github.cangoksel.belge.Belge;
import com.github.cangoksel.belge.BelgeModel;
import com.github.cangoksel.belge.BelgeRepository;
import com.github.cangoksel.belge.BelgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by herdemir on 14.12.2015.
 */
@Service
@Transactional
public class BelgeServiceImpl implements BelgeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BelgeServiceImpl.class);

    @Autowired
    private GridFsTemplate gridFs;
    @Autowired
    private BelgeRepository belgeRepository;

    @Override
    public Belge belgeEkle(final BelgeModel belgeModel) {
        return belgeEkle(Collections.singletonList(belgeModel)).get(0);
    }

    @Override
    public List<Belge> belgeEkle(final Collection<BelgeModel> belgeler) {
        final List<Belge> eklenenBelgeler = new ArrayList<>();

        for (final BelgeModel belgeModel : belgeler) {
            try {
                try (final InputStream uploadedFileStream = belgeModel.getUploadedFileStream()) {
                    final Belge belge = new Belge(
                        belgeModel.getBelgeTipi(),
                        belgeModel.getBelgeAdi(),
                        belgeModel.getBelgeBoyutu(),
                        belgeModel.getAciklama()
                    );
                    belgeEkleInternal(belge, uploadedFileStream);
                    eklenenBelgeler.add(belge);
                }
            } catch (IOException e) {
                LOGGER.error("Belge eklenirken hata alındı.", e);
            }
        }
        return eklenenBelgeler;
    }

    @Override
    public void belgeEkle(final Belge belge, final InputStream inputstream) {
        belgeEkleInternal(belge, inputstream);
    }

    private void belgeEkleInternal(final Belge belge, final InputStream inputstream) {
        gridFs.store(inputstream, belge.getBelgeAdiString(), belge);
    }

    @Override
    public void belgeSil(final Collection<Belge> belgeler) {
        for (final Belge belge : belgeler) {
            gridFs.delete(Query.query(Criteria.where("metadata._id").is(belge.getId())));
        }
    }

    @Override
    public Belge belgeById(UUID id) {
        return belgeRepository.findOne(id);
    }

    @Override
    public Belge saveBelge(Belge belge) {
        return belgeRepository.save(belge);
    }

    @Override
    public void belgeSil(final Belge belge) {
        belgeSil(Collections.singletonList(belge));
    }
}
