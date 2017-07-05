package belge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by usuicmez on 04.05.2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "content")
public class BelgeAlRestModel implements Serializable {
    private long legaliteSistemNo;
    private byte[] content;
    private BelgeTipi belgeTipi;
    private String belgeAdi;
    private String id;
}
