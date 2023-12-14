package org.greatgamesonly.core.universalcalculator.model.validation.base;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OpenApiCustomizer;

import java.lang.reflect.Field;
import java.util.Optional;

public abstract class CustomOpenApiCustomizer implements OpenApiCustomizer {

    protected Schema<?> getSchemaBasedOnField(Components components, Field field) {
        Schema<?> schema = components.getSchemas().get(field.getDeclaringClass().getSimpleName());
        schema = schema == null ? components.getSchemas().get(field.getDeclaringClass().getSimpleName() + "Object") : schema;
        schema = schema != null ? (Schema<?>) schema.getProperties().get(field.getName()) : null;
        return schema;
    }

    protected void addValueToDescription(Schema<?> propertySchema, Object valueToAdd) {
        if (propertySchema != null) {
            propertySchema.setDescription(Optional.ofNullable(propertySchema.getDescription())
                    .orElse("") + valueToAdd + "<br>");
        }
    }

}
