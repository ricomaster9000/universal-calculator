package org.greatgamesonly.core.universalcalculator.model.validation.unique;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.persistence.Column;
import org.greatgamesonly.core.universalcalculator.model.validation.base.CustomOpenApiCustomizer;
import org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.ALL_INTERNAL_FULL_CLASS_NAMES;

@Component
public class UniqueOpenApiCustomizer extends CustomOpenApiCustomizer {

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
                        Schema<?> propertySchema = getSchemaBasedOnField(components, field);
                        addValueToDescription(propertySchema," Unique: " + uniqueAnnotation.unique());
                    }
                }
            }
        }
    }
}
