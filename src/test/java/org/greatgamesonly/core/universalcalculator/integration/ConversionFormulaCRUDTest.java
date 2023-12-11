package org.greatgamesonly.core.universalcalculator.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.greatgamesonly.core.universalcalculator.SpringBootTestWrapper;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.ConversionFormula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.model.domain.ConstantEntities.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTestWrapper
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ConversionFormulaCRUDTest {

    public static final ConversionFormula TEST_CONVERSION_FORMULA = new ConversionFormula()
            .withName("TEST Conversion Formula")
            .withDescription("Takes a CONVERSION_FROM measurement unit input param and " +
                    "a CONVERSION_TO measurement unit input param, " +
                    "then gets the relevant conversion ratio and applies it.")
            .withFormulaParameterUsageInfo(new ArrayList<>(List.of(
                    CONVERSION_FROM_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC
            )))
            .withPossibleFormulaParams(new ArrayList<>(List.of(
                    ALL_MEASUREMENT_UNITS
            )));

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

    @Test
    public void A_testCreateConversionFormula() throws Exception {

        Formula<?> newFormula = TEST_CONVERSION_FORMULA;
        String jsonRequest = objectMapper.writeValueAsString(newFormula);

        MvcResult result = mockMvc.perform(post("/api/v1/formula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(newFormula.getName())))
                .andReturn();

        TEST_CONVERSION_FORMULA.setId(objectMapper.readValue(result.getResponse().getContentAsString(), ConversionFormula.class).getId());
    }

    @Test
    public void B_testGetConversionFormulaById() throws Exception {
        mockMvc.perform(get("/api/v1/formula/{id}", TEST_CONVERSION_FORMULA.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(TEST_CONVERSION_FORMULA.getId().intValue())))
                .andExpect(jsonPath("$.name", is(TEST_CONVERSION_FORMULA.getName())));
    }

    @Test
    public void C_testGetAllConversionFormulas() throws Exception {
        mockMvc.perform(get("/api/v1/formula"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void D_testUpdateConversionFormula() throws Exception {
        Formula<?> updatedFormula = TEST_CONVERSION_FORMULA;
        updatedFormula.setName("Updated Conversion Formula");
        String jsonRequest = objectMapper.writeValueAsString(updatedFormula);

        mockMvc.perform(put("/api/v1/formula/{id}", TEST_CONVERSION_FORMULA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(TEST_CONVERSION_FORMULA.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Updated Conversion Formula")));
    }

    @Test
    public void Z_testDeleteConversionFormula() throws Exception {
        mockMvc.perform(delete("/api/v1/formula/{id}", TEST_CONVERSION_FORMULA.getId()))
                .andExpect(status().isNoContent());
    }
}
