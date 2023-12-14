package org.greatgamesonly.core.universalcalculator.model.validation.unique;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.persistence.Column;
import org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.ALL_INTERNAL_FULL_CLASS_NAMES;

@Component
public class UniqueOpenApiCustomizer implements OpenApiCustomizer {

    @Override
    public void customise(OpenAPI openApi) {
        Components components = openApi.getComponents();
        if (components != null) {
            List<? extends Class<?>> allTypes = ALL_INTERNAL_FULL_CLASS_NAMES().stream()
                    .map(ReflectionUtils::getClassByName)
                    .filter(Objects::nonNull)
                    .toList();
            for(Class<?> clazz : allTypes) {
                for (Field field : ReflectionUtils.getClassFields(clazz)) {
                    Column uniqueAnnotation = field.getAnnotation(Column.class);
                    if(uniqueAnnotation != null && field.getType().equals(String.class)) {
                        Schema<?> schema = components.getSchemas().get(field.getDeclaringClass().getSimpleName());
                        schema = schema == null ? components.getSchemas().get(field.getDeclaringClass().getSimpleName() + "Object") : schema;
                        Schema<?> propertySchema = schema != null ? (Schema<?>) schema.getProperties().get(field.getName()) : null;
                        if (propertySchema != null) {
                            propertySchema.set$comment(String.valueOf(uniqueAnnotation.unique()));
                            propertySchema.setDescription(Optional.ofNullable(propertySchema.getDescription())
                                    .orElse("") + " Unique: " + uniqueAnnotation.unique());
                        }
                    }
                }
            }
        }
    }
}
