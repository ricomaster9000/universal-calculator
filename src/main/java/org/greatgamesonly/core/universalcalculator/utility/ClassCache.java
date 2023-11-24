package org.greatgamesonly.core.universalcalculator.utility;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassCache {

    private static final int maxEntries = 100000;
    private static final Map<String, Class<?>> cache = new LinkedHashMap<String, Class<?>>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Class<?>> eldest) {
            // When to remove the eldest entry.
            return size() > maxEntries; // Size exceeds the max limit
        }
    };

    public static Class<?> getClassForName(String className) throws ClassNotFoundException {
        return cache.computeIfAbsent(className, key -> {
            try {
                return Class.forName(key);
            } catch (ClassNotFoundException e) {
                // Handle exception or rethrow as unchecked
                throw new RuntimeException(e);
            }
        });
    }
}