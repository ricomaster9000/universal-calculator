package org.greatgamesonly.core.universalcalculator.configuration;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.*;

@Component
public class CacheAllCacheNames {
    private static final Logger logger = LoggerFactory.getLogger(CacheAllCacheNames.class);

    @EventListener()
    public void onApplicationEvent(ContextRefreshedEvent event) throws ClassNotFoundException {
        ApplicationContext ctx = event.getApplicationContext();

        try {
            logger.info("Getting all internal full class names to populate ALL_INTERNAL_FULL_CLASS_NAMES");
            Reflections reflections = new Reflections(CORE_PACKAGE_NAME);
            reflections.merge(new Reflections());

            List<String> allRelevantTypes = reflections.getAllTypes().stream()
                    .filter(className -> className != null && className.startsWith(CORE_PACKAGE_NAME))
                    .toList();

            logger.info("Adding " + allRelevantTypes.size() + " internal full class names to ALL_INTERNAL_FULL_CLASS_NAMES");
            set_ALL_INTERNAL_FULL_CLASS_NAMES(allRelevantTypes);

        } catch (Exception e) {
            logger.error("unable to setup ALL_INTERNAL_FULL_CLASS_NAMES, " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
