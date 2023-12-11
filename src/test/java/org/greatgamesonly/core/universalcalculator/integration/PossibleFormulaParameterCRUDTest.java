package org.greatgamesonly.core.universalcalculator.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.greatgamesonly.core.universalcalculator.SpringBootTestWrapper;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.MeasurementUnit;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.PossibleFormulaParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.greatgamesonly.core.universalcalculator.model.domain.ConstantEntities.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTestWrapper
@TestMethodOrder(MethodOrderer.MethodName.class)
public class PossibleFormulaParameterCRUDTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final MeasurementUnit possibleFormulaParameterTest = MILE;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void A_testCreate() throws Exception {
        possibleFormulaParameterTest.setId(999999L);
        String jsonRequest = objectMapper.writeValueAsString(possibleFormulaParameterTest);

        MvcResult result = mockMvc.perform(post("/api/v1/possible-formula-parameter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(possibleFormulaParameterTest.getName())))
                .andReturn();

        possibleFormulaParameterTest.setId(objectMapper.readValue(result.getResponse().getContentAsString(), PossibleFormulaParameter.class).getId());
    }

    @Test
    public void B_testGetById() throws Exception {
        mockMvc.perform(get("/api/v1/possible-formula-parameter/{id}", possibleFormulaParameterTest.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(possibleFormulaParameterTest.getId().intValue())))
                .andExpect(jsonPath("$.name", is(possibleFormulaParameterTest.getName())));
    }

    @Test
    public void C_testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/possible-formula-parameter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[?(@.name == '%s')]", possibleFormulaParameterTest.getName()).exists());
    }

    @Test
    public void D_testUpdate() throws Exception {
        MeasurementUnit updatedFormulaParameter = new MeasurementUnit(
                possibleFormulaParameterTest.getId(),
                "UpdatedName",
                possibleFormulaParameterTest.getDescription(),
                possibleFormulaParameterTest.getInputParamSpecification(),
                possibleFormulaParameterTest.getMeasurementSystem()
        );

        String jsonRequest = objectMapper.writeValueAsString(updatedFormulaParameter);

        mockMvc.perform(put("/api/v1/possible-formula-parameter/{id}", possibleFormulaParameterTest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updatedFormulaParameter.getName())));
    }

    @Test
    public void Z_testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/possible-formula-parameter/{id}", possibleFormulaParameterTest.getId()))
                .andExpect(status().isNoContent());

        // Optionally, you can add a check to confirm that the resource has been deleted
        // by attempting to fetch it and expecting a 'Not Found' status.
        mockMvc.perform(get("/api/v1/possible-formula-parameter/{id}", possibleFormulaParameterTest.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void Y_testInvalidNameWithHyphen() throws Exception {
        // Create a new PossibleFormulaParameter with an invalid name (containing a hyphen)
        PossibleFormulaParameter invalidFormulaParameter = new MeasurementUnit(
                null, "Invalid-Name", "Invalid Description", null, null);

        // Test creating an entity with an invalid name
        String createJsonRequest = objectMapper.writeValueAsString(invalidFormulaParameter);
        mockMvc.perform(post("/api/v1/possible-formula-parameter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonRequest))
                .andExpect(status().isBadRequest()); // Expecting bad request due to validation failure

        // Assuming the entity was initially created without a hyphen and later updated to an invalid name
        MeasurementUnit updatedFormulaParameter = new MeasurementUnit(
                possibleFormulaParameterTest.getId(),
                "Updated-InvalidName",
                possibleFormulaParameterTest.getDescription(),
                possibleFormulaParameterTest.getInputParamSpecification(),
                possibleFormulaParameterTest.getMeasurementSystem());

        // Test updating an entity to an invalid name
        String updateJsonRequest = objectMapper.writeValueAsString(updatedFormulaParameter);
        mockMvc.perform(put("/api/v1/possible-formula-parameter/{id}", possibleFormulaParameterTest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonRequest))
                .andExpect(status().isBadRequest()); // Expecting bad request due to validation failure
    }
}
