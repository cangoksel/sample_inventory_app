package belge;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by herdemir on 11.10.2016.
 */
public interface BelgeIslemleri {
    Set<BelgeModel> listBelgeler(UUID id, boolean includeDeletedBelgeler);

    Map<UUID, Path> saveBelgeler(UUID id, List<BelgeModel> belgeler);

    void updateBelge(BelgeViewModel model);

    void belgeSilGeriAl(UUID belgeId);
}
