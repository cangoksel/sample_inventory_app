package com.github.cangoksel.user.model;

import com.github.cangoksel.belge.BelgeTipi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
