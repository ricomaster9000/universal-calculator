package org.greatgamesonly.core.universalcalculator.unit_test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.greatgamesonly.core.universalcalculator.configuration.FormulaDeserializer;
import org.greatgamesonly.core.universalcalculator.domain.ConstantEntities;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FormulaDeserializerTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeserialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA);

        JsonParser jsonParser = objectMapper.createParser(json);

        FormulaDeserializer deserializer = new FormulaDeserializer();
        Formula<?, ?> formula = deserializer.deserialize(jsonParser, objectMapper.getDeserializationContext());

        // Perform assertions
        assertNotNull(formula);
        assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getName(), formula.getName());
        assertNotNull(formula.getId(), "The formula's ID should not be null.");
        assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getDescription(), formula.getDescription(), "The descriptions should be equal.");
        assertNotNull(formula.getFormulaType(), "The formula type should not be null.");
        assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaType().getId(), formula.getFormulaType().getId(), "The formula type IDs should be equal.");
        assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaParameterUsageInfo().size(), formula.getFormulaParameterUsageInfo().size(), "The number of formula parameter usage info should be equal.");

        // Assert details of formula parameters if necessary
        for (int i = 0; i < formula.getFormulaParameterUsageInfo().size(); i++) {
            assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaParameterUsageInfo().get(i).getParameterPlaceholderName(),
                    formula.getFormulaParameterUsageInfo().get(i).getParameterPlaceholderName(), "The parameter placeholder names should be equal.");
            assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaParameterUsageInfo().get(i).getDescription(),
                    formula.getFormulaParameterUsageInfo().get(i).getDescription(), "The parameter descriptions should be equal.");
        }

        // Assert details of possible formula parameters if necessary
        assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getPossibleFormulaParameters().size(), formula.getPossibleFormulaParameters().size(), "The number of possible formula parameters should be equal.");
        for (int i = 0; i < formula.getPossibleFormulaParameters().size(); i++) {
            assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getPossibleFormulaParameters().get(i).getName(),
                    formula.getPossibleFormulaParameters().get(i).getName(), "The names of possible formula parameters should be equal.");
            assertEquals(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getPossibleFormulaParameters().get(i).getDescription(),
                    formula.getPossibleFormulaParameters().get(i).getDescription(), "The descriptions of possible formula parameters should be equal.");
        }
    }
}
