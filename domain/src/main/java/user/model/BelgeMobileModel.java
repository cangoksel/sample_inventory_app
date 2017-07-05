package user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.innova.sample.belge.BelgeTipi;

/**
 * Created by tozyurek on 02.08.2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BelgeMobileModel {
    private String name;
    private String mimeType;
    private byte[] content;
    private BelgeTipi belgeTipi;
}
