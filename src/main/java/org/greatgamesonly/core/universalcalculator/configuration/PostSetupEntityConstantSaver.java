package org.greatgamesonly.core.universalcalculator.configuration;

import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.domain.constant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils.getAllConstantValuesInClass;

@Component
public class PostSetupEntityConstantSaver {
    private static final Logger logger = LoggerFactory.getLogger(PostSetupEntityConstantSaver.class);

    @EventListener()
    public void onApplicationEvent(ContextRefreshedEvent event) throws ClassNotFoundException {
        ApplicationContext ctx = event.getApplicationContext();

        try {
            List<Object> constantEntities = getAllConstantValuesInClass(InputParamSpecificationConstants.class);
            constantEntities.addAll(getAllConstantValuesInClass(FormulaParameterInputSpecificationConstants.class));
            constantEntities.addAll(getAllConstantValuesInClass(MeasurementUnitSystemConstants.class));
            constantEntities.addAll(getAllConstantValuesInClass(MeasurementUnitConstants.class));
            constantEntities.addAll(getAllConstantValuesInClass(MeasurementUnitToMeasurementUnitConversionFactorConstants.class));
            constantEntities.addAll(getAllConstantValuesInClass(ConversionFormulaConstants.class));

            for (Object constantVal : constantEntities) {
                if (constantVal != null) {
                    saveConstantEntity(constantVal, ctx);
                }
            }
        } catch (Exception e) {
            logger.error("unable to setup constant entities, " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void saveConstantEntity(Object constantEntityValue, ApplicationContext ctx) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info("checking class " + constantEntityValue.getClass().getSimpleName());
        if (constantEntityValue.getClass().isAnnotationPresent(LinkedRepository.class)) {
            LinkedRepository linkedRepository = constantEntityValue.getClass().getAnnotation(LinkedRepository.class);
            Object repoBean = ctx.getBean(linkedRepository.value());
            Method saveAllMethod = linkedRepository.value().getMethod("saveEntityImmediately", Object.class);
            logger.info("found a constant entity field to persist to database");
            BaseEntity createdEntity = ((BaseEntity)saveAllMethod.invoke(repoBean, constantEntityValue));
            ((BaseEntity) constantEntityValue).setId(createdEntity.getId());
        }
    }

}
