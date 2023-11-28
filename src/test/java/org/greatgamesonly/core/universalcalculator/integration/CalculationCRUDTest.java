package org.greatgamesonly.core.universalcalculator.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.greatgamesonly.core.universalcalculator.SpringBootTestWrapper;
import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculationInputParam;
import org.greatgamesonly.core.universalcalculator.domain.ConstantEntities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTestWrapper
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CalculationCRUDTest {

    final Long PREDEFINED_CALCULATION_ID = 1L;
    final String PREDEFINED_CALCULATION_NAME = "Test Calculation";
    final String PREDEFINED_CALCULATION_DESCRIPTION = "This is a test calculation.";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    // Test for creating a new calculation
    @Test
    public void A_testCreateCalculation() throws Exception {
        CalculateRequest newCalculation = createTestCalculation();
        String jsonRequest = objectMapper.writeValueAsString(newCalculation);

        mockMvc.perform(post("/api/v1/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", is(newCalculation.getCalculationsToPerform().get(0).getName())));
    }

    // Test for retrieving a calculation by ID
    @Test
    public void B_testGetCalculation() throws Exception {
        // Assuming the calculation with ID 1 exists
        mockMvc.perform(get("/api/v1/calculation/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(not(emptyOrNullString()))));
    }

    // Test for retrieving all calculations
    @Test
    public void C_testGetAllCalculations() throws Exception {
        mockMvc.perform(get("/api/v1/calculation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    // Test for updating a calculation
    @Test
    public void D_testUpdateCalculation() throws Exception {
        // Assuming the calculation with ID 1 exists and we want to update it
        Calculation updatedCalculation = createTestCalculation().getCalculationsToPerform().get(0);
        updatedCalculation.setName("Updated Name");
        String jsonRequest = objectMapper.writeValueAsString(updatedCalculation);

        mockMvc.perform(put("/api/v1/calculation/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Name")));
    }

    // Test for deleting a calculation
    @Test
    public void Z_testDeleteCalculation() throws Exception {
        // Assuming the calculation with ID 1 exists and we want to delete it
        mockMvc.perform(delete("/api/v1/calculation/{id}", 1))
                .andExpect(status().isOk());
    }

    private CalculateRequest createTestCalculation() {
        Calculation calculation = new Calculation();
        calculation.setName(PREDEFINED_CALCULATION_NAME);
        calculation.setDescription(PREDEFINED_CALCULATION_DESCRIPTION);

        CalculationInputParam inputParamFrom = new CalculationInputParam(
                ConstantEntities.METER.getName(), "CONVERSION_FROM", 1.0
        );
        CalculationInputParam inputParamTo = new CalculationInputParam(
                ConstantEntities.FOOT.getName(), "CONVERSION_TO"
        );
        calculation.setCalculationInputParams(Arrays.asList(inputParamFrom, inputParamTo));

        calculation.setOutput(BigDecimal.valueOf(3.28084));

        return new CalculateRequest(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA, new ArrayList<>(List.of(calculation)));
    }

    private Calculation createAndPostTestCalculation() throws Exception {
        CalculateRequest calculateRequest = createTestCalculation();
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculateRequest)))
                .andReturn();
        Calculation[] calculations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Calculation[].class);
        return calculations[0];
    }
}
