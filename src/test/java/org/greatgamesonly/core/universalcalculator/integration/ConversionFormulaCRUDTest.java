package org.greatgamesonly.core.universalcalculator.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.greatgamesonly.core.universalcalculator.SpringBootTestWrapper;
import org.greatgamesonly.core.universalcalculator.domain.ConstantEntities;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.FormulaRequest;
import org.greatgamesonly.core.universalcalculator.domain.formula.conversion.ConversionFormula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTestWrapper
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ConversionFormulaCRUDTest {

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
        FormulaRequest newFormula = new FormulaRequest(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA);
        String jsonRequest = objectMapper.writeValueAsString(newFormula);

        mockMvc.perform(post("/api/v1/formula")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(newFormula.getFormula().getId().intValue())))
                .andExpect(jsonPath("$.name", is(newFormula.getFormula().getName())));
    }

    @Test
    public void B_testGetConversionFormulaById() throws Exception {
        mockMvc.perform(get("/api/v1/formula/{id}", ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getId())
                        .param("formulaTypeId", String.valueOf(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaType().getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getId().intValue())))
                .andExpect(jsonPath("$.name", is(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getName())));
    }

    @Test
    public void C_testGetAllConversionFormulas() throws Exception {
        mockMvc.perform(get("/api/v1/formula"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    public void D_testUpdateConversionFormula() throws Exception {
        FormulaRequest updatedFormula = new FormulaRequest(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA);
        updatedFormula.getFormula().setName("Updated Conversion Formula");
        String jsonRequest = objectMapper.writeValueAsString(updatedFormula);

        mockMvc.perform(put("/api/v1/formula/{id}", ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Updated Conversion Formula")));
    }

    @Test
    public void Z_testDeleteConversionFormula() throws Exception {
        mockMvc.perform(delete("/api/v1/formula/{id}", ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getId())
                        .param("formulaTypeId", String.valueOf(ConstantEntities.GENERIC_SIMPLE_CONVERSION_FORMULA.getFormulaType().getId())))
                .andExpect(status().isNoContent());
    }
}
