package com.kingprice.insurance.springworkassessment;

import java.util.HashSet;
import java.util.Set;

public class GlobalConstants {
    public static final String DOMAIN_MODEL_PACKAGE_NAME = "com.kingprice.insurance.springworkassessment.domain";

    public static final int STANDARD_DB_STRING_MAX_CHAR_SIZE = 255;
    public static final int SHORT_DB_TEXT_MAX_CHAR_SIZE = 3000;
    public static final int LONG_DB_TEXT_MAX_CHAR_SIZE = 15000;

    public static final Set<Class<?>> CACHED_FORMULA_SUBCLASSES = new HashSet<Class<?>>();
}
