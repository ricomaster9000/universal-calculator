package com.kingprice.insurance.springworkassessment.configuration;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import jakarta.persistence.Entity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.web.context.support.StandardServletEnvironment;

import static org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils.*;

@Component
public class PostSetupEntityConstantSaver {
    private static final Logger logger = LoggerFactory.getLogger(PostSetupEntityConstantSaver.class);
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ctx = event.getApplicationContext();

        try {

            List<Class<?>> possibleEntityDomainClasses = findAllClassesUsingReflectionsLibrary("com.kingprice.insurance.springworkassessment");
            
            for(Class<?> clazz : possibleEntityDomainClasses) {
                saveConstantEntities(clazz,ctx);
            }

            final Metamodel mm = em.getMetamodel();
            for (final ManagedType<?> managedType : mm.getManagedTypes()) {
                aveConstantEntities(managedType.getJavaType(),ctx);
            }
        } catch(IllegalAccessException | InvocationTargetException | IOException | NoSuchFieldException |
                ClassNotFoundException | NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    }

    public List<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
          .stream()
          .collect(Collectors.toList());
    }

    private void saveConstantEntities(Class<?> clazz, ApplicationContext ctx) throws NoSuchMethodException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        logger.info("checking class " + clazz.getSimpleName());
        if (clazz.isAnnotationPresent(LinkedRepository.class)) {
            logger.info("checking if entity class " + clazz.getSimpleName() + " has any constant entity fields to persist");
            LinkedRepository linkedRepository = clazz.getAnnotation(LinkedRepository.class);
            Object repoBean = ctx.getBean(linkedRepository.value());
            Method saveAllMethod = linkedRepository.value().getMethod("saveAllEntitiesImmediately", Iterable.class);
            List<Object> constantEntityValues = getAllConstantValuesInClass(clazz).stream()
                    .filter(constantVal -> constantVal.getClass().isAnnotationPresent(Entity.class) && clazz.isAssignableFrom(constantVal.getClass()))
                    .toList();

            List<Field> constantEntityFields = List.of(getClassFields(clazz));
            
            logger.info("found " + constantEntityFields.size() + " constant entity fields to persist to datavase");
            
            for(Field field : constantEntityFields) {
                if(!field.getType().equals(clazz)) {
                    saveConstantEntities(field.getType(), ctx);
                }
            }

            if (constantEntityValues.size() > 0) {
                saveAllMethod.invoke(repoBean, constantEntityValues);
            }
        }
    }

}
