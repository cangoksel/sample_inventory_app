package common.utils;

import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Strings;

/**
 * Created by herdemir on 17.02.2016.
 */
public final class ConfigUtils {
    private ConfigUtils() {
    }

    public static boolean isDevelopment() {
        final String property = Strings.nullToEmpty(StandardSystemProperty.OS_NAME.value());
        return property.isEmpty() || property.startsWith("Windows");
    }
}
