package org.greatgamesonly.core.universalcalculator;

import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GlobalConstants {

    public static final int STANDARD_DB_STRING_MAX_CHAR_SIZE = 255;
    public static final int SHORT_DB_TEXT_MAX_CHAR_SIZE = 3000;
    public static final int LONG_DB_TEXT_MAX_CHAR_SIZE = 15000;

    public static final Set<Class<?>> CACHED_FORMULA_SUBCLASSES = new HashSet<Class<?>>();

    public static final Map<Class<?>, BaseFormulaRepository<? extends Formula<?,?>>> CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES = new HashMap<>();
}
