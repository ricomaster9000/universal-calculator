package org.greatgamesonly.core.universalcalculator.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.utility.ClassCache;

import java.io.IOException;

public class FormulaDeserializer extends JsonDeserializer<Formula<?>> {

    @Override
    public Formula<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        // Extract the formula type name or class name
        String formulaTypeName = node.get("formulaType").get("linkedFormulaSubClassName").asText();

        try {
            Class<?> clazz = ClassCache.getClassForName(formulaTypeName);

            // Check if we are trying to deserialize into the base Formula class or a subclass
            if (clazz.equals(Formula.class)) {
                // Possibly throw an exception, because we expect a subclass of Formula, not the base class itself.
                throw new JsonProcessingException("Cannot deserialize into the base Formula type: " + formulaTypeName) {};
            } else if (Formula.class.isAssignableFrom(clazz)) {
                // We are dealing with a subclass of Formula, proceed with deserialization
                return (Formula<?>) jp.getCodec().treeToValue(node, clazz);
            }
        } catch (ClassNotFoundException e) {
            throw new JsonProcessingException("Unknown formula type: " + formulaTypeName) {};
        }

        // If the class couldn't be determined or is the base class, throw an exception or return null
        throw new JsonProcessingException("Could not deserialize Formula: " + formulaTypeName) {};
    }
}