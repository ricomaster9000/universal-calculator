package org.greatgamesonly.core.universalcalculator.model.validation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils;
import org.reflections.Reflections;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class MaxDoubleOpenApiCustomizer implements OpenApiCustomizer {

    @Override
    public void customise(OpenAPI openApi) {
        Reflections reflections = new Reflections();
        Reflections reflectionsAlternative = new Reflections("org.greatgamesonly.core.universalcalculator");
        reflections.merge(reflectionsAlternative);

        Components components = openApi.getComponents();
        if (components != null) {
            List<? extends Class<?>> allTypes = reflections.getAllTypes().stream()
                    .map(ReflectionUtils::getClassByName)
                    .filter(clazz -> clazz.getPackageName().startsWith("org.greatgamesonly.core.universalcalculator"))
                    .toList();
            for(Class<?> clazz : allTypes) {
                for (Field field : ReflectionUtils.getClassFields(clazz)) {
                    MaxDouble maxDoubleAnnotation = field.getAnnotation(MaxDouble.class);
                    if(maxDoubleAnnotation != null) {
                        Schema<?> schema = components.getSchemas().get(field.getDeclaringClass().getSimpleName());
                        Schema<?> propertySchema = schema != null ? (Schema<?>) schema.getProperties().get(field.getName()) : null;
                        if (propertySchema != null) {
                            propertySchema.setMaximum(BigDecimal.valueOf(maxDoubleAnnotation.value()));
                            propertySchema.setDescription(Optional.ofNullable(propertySchema.getDescription())
                                    .orElse("") + " Max: " + maxDoubleAnnotation.value());
                        }
                    }
                }
            }
        }
    }
}