package common.utils;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;

/**
 * Created by tozyurek on 26.06.2015.
 */

@Getter
@Setter
@AllArgsConstructor
public class NavigationStackModel {
    private String viewName;
    private String viewKey;
    private Map<String,Object> params = Maps.newHashMap();
    private Collection viewParams;
    private final Map<String, String> requestParams= Maps.newHashMap();;
    private boolean link;
}
