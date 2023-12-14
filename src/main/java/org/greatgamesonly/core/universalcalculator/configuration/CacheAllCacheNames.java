package org.greatgamesonly.core.universalcalculator.configuration;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.ALL_INTERNAL_FULL_CLASS_NAMES;

@Component
public class CacheAllCacheNames {
    private static final Logger logger = LoggerFactory.getLogger(CacheAllCacheNames.class);

    @EventListener()
    public void onApplicationEvent(ContextRefreshedEvent event) throws ClassNotFoundException {
        ApplicationContext ctx = event.getApplicationContext();

        try {
            logger.info("Getting all internal full class names to populate ALL_INTERNAL_FULL_CLASS_NAMES");
            Reflections reflections = new Reflections("org.greatgamesonly.core.universalcalculator");
            if(reflections.getAllTypes().size() <= 0) {
                reflections.merge(new Reflections());
            }

            List<String> allRelevantTypes = reflections.getAllTypes().stream()
                    .filter(className -> className != null && className.startsWith("org.greatgamesonly.core.universalcalculator"))
                    .toList();

            ALL_INTERNAL_FULL_CLASS_NAMES.addAll(allRelevantTypes);

        } catch (Exception e) {
            logger.error("unable to setup ALL_INTERNAL_FULL_CLASS_NAMES, " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
