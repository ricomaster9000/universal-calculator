package org.greatgamesonly.core.universalcalculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GlobalConstants {

    public static final int STANDARD_DB_STRING_MAX_CHAR_SIZE = 255;
    public static final int SHORT_DB_TEXT_MAX_CHAR_SIZE = 3000;
    public static final int LONG_DB_TEXT_MAX_CHAR_SIZE = 15000;

    public static final String SQL_MAX_DOUBLE_COLUMN_DEFINITION = "DOUBLE";
    public static final double SQL_MAX_DOUBLE = 99999999999999999999999999999999999999999999999999999999.99999999D;
    public static final String JAVADOCS_SPECIAL_KEY = "2y19F09NuVw3vgMvVq1dWx0xQu9-7WVhUJJz0sUrcNMtB6toF5lV3l7m-brtf6TPZrySw45nc8rK9kkNg5wca9ZTF-jvMPA7a9c8X9FkQbydk6qUHpsg4Zd2Yy";
    public static final String CORE_PACKAGE_NAME = "org.greatgamesonly.core.universalcalculator";
    private static final Set<String> ALL_INTERNAL_FULL_CLASS_NAMES = new HashSet<>();
    public static Set<String> ALL_INTERNAL_FULL_CLASS_NAMES() {
        // check if ALL_INTERNAL_FULL_CLASS_NAMES has been populated, if not assume it is being populated and wait 60 seconds
        if(ALL_INTERNAL_FULL_CLASS_NAMES.size() <= 0) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ignore) {}
        }
        return ALL_INTERNAL_FULL_CLASS_NAMES;
    }
    public static void set_ALL_INTERNAL_FULL_CLASS_NAMES(List<String> aALL_INTERNAL_FULL_CLASS_NAMES) {
        ALL_INTERNAL_FULL_CLASS_NAMES.addAll(aALL_INTERNAL_FULL_CLASS_NAMES);
    }

    public static final int NETWORK_REQUEST_TIMEOUT_STANDARD = 120000; // 120 seconds
}
