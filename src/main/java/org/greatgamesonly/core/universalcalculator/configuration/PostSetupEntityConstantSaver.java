package org.greatgamesonly.core.universalcalculator.configuration;

import jakarta.persistence.Entity;
import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.ConstantEntities;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CACHED_FORMULA_SUBCLASSES;
import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES;
import static org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils.getAllConstantValuesInClass;

@Component
public class PostSetupEntityConstantSaver {
    private static final Logger logger = LoggerFactory.getLogger(PostSetupEntityConstantSaver.class);
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws ClassNotFoundException {
        ApplicationContext ctx = event.getApplicationContext();

        try {
            List<Object> constantEntities = getAllConstantValuesInClass(ConstantEntities.class);

            for (Object constantVal : constantEntities) {
                if (constantVal != null) {
                    saveConstantEntity(constantVal, ctx);
                }
            }
        } catch (Exception e) {
            logger.error("unable to setup constant entities, " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        Reflections reflections = new Reflections("org.greatgamesonly.core");

        /*
         I tried pinpointing the Reflections object instance to target the web app package name, but
         then it does not find the Formula subclasses, I have a feeling Spring is behind this, but
         if I set the Reflections to scan everything it will find but it is very slow
         */
        Set<Class<? extends Formula>> possibleFormulaSubClasses = reflections.getSubTypesOf(Formula.class);
        for(Class<?> possibleFormulaSubClass : possibleFormulaSubClasses) {
            if(Formula.class.isAssignableFrom(possibleFormulaSubClass)) {
                CACHED_FORMULA_SUBCLASSES.add(possibleFormulaSubClass);
            }
        }

        for(Class<?> formulaClass : CACHED_FORMULA_SUBCLASSES) {
            try {
                CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES.put(
                        formulaClass,
                        getFormulaRepositoryGeneric(ctx,(Formula<?, ?>) formulaClass.getConstructor().newInstance())
                );
            } catch(IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }

    }

    private BaseFormulaRepository<? extends Formula<?,?>> getFormulaRepositoryGeneric(ApplicationContext ctx, Formula<?,?> formula) {
        return ctx.getBean(formula.getFormulaRepositoryClass());
    }

    private void saveConstantEntity(Object constantEntityValue, ApplicationContext ctx) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info("checking class " + constantEntityValue.getClass().getSimpleName());
        if (constantEntityValue.getClass().isAnnotationPresent(LinkedRepository.class)) {
            LinkedRepository linkedRepository = constantEntityValue.getClass().getAnnotation(LinkedRepository.class);
            Object repoBean = ctx.getBean(linkedRepository.value());
            Method saveAllMethod = linkedRepository.value().getMethod("saveEntityImmediately", Object.class);
            logger.info("found a constant entity field to persist to database");
            ((BaseEntity) constantEntityValue).setId(((BaseEntity)saveAllMethod.invoke(repoBean, constantEntityValue)).getId());
        }
    }

}
