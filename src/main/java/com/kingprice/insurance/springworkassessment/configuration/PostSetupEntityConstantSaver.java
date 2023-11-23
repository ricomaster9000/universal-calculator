package com.kingprice.insurance.springworkassessment.configuration;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.ConstantEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils.getAllConstantValuesInClass;

@Component
public class PostSetupEntityConstantSaver {
    private static final Logger logger = LoggerFactory.getLogger(PostSetupEntityConstantSaver.class);
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ctx = event.getApplicationContext();

        try {
            List<Object> constantEntities = getAllConstantValuesInClass(ConstantEntities.class);
            
            for(Object constantVal : constantEntities) {
                if(constantVal != null) {
                    saveConstantEntity(constantVal, ctx);
                }
            }
        } catch(IllegalAccessException | InvocationTargetException | IOException | NoSuchFieldException |
                ClassNotFoundException | NoSuchMethodException | IntrospectionException e){
            throw new RuntimeException(e);
        }
    }

    private void saveConstantEntity(Object constantEntityValue, ApplicationContext ctx) throws NoSuchMethodException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, IntrospectionException {
        logger.info("checking class " + constantEntityValue.getClass().getSimpleName());
        if (constantEntityValue.getClass().isAnnotationPresent(LinkedRepository.class)) {
            LinkedRepository linkedRepository = constantEntityValue.getClass().getAnnotation(LinkedRepository.class);
            Object repoBean = ctx.getBean(linkedRepository.value());
            Method saveAllMethod = linkedRepository.value().getMethod("saveAllEntitiesImmediately", Iterable.class);
            logger.info("found a constant entity fields to persist to database");
            try {
                saveAllMethod.invoke(repoBean, new ArrayList<>(List.of(constantEntityValue)));
            } catch(Exception e) {
                logger.info("dsfsdfsdf");
            }
        }
    }

}
