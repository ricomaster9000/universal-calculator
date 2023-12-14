package org.greatgamesonly.core.universalcalculator.model.validation.mindouble;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import org.greatgamesonly.core.universalcalculator.model.validation.base.CustomOpenApiCustomizer;
import org.greatgamesonly.opensource.utils.reflectionutils.ReflectionUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.ALL_INTERNAL_FULL_CLASS_NAMES;

@Component
public class MinDoubleOpenApiCustomizer extends CustomOpenApiCustomizer {

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
                    MinDouble minDoubleAnnotation = field.getAnnotation(MinDouble.class);
                    if(minDoubleAnnotation != null) {
                        Schema<?> propertySchema = getSchemaBasedOnField(components, field);
                        if (propertySchema != null) {
                            propertySchema.setMinimum(BigDecimal.valueOf(minDoubleAnnotation.value()));
                        }
                    }
                }
            }
        }
    }
}
