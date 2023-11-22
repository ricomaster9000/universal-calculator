package com.kingprice.insurance.springworkassessment.integration;

import com.kingprice.insurance.springworkassessment.SpringBootTestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingprice.insurance.springworkassessment.domain.calculation.CalculateRequest;
import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.domain.calculation.CalculationInputParam;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@SpringBootTestWrapper
public class CalculationServiceIntegrationTest {

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
    public void testFeetToMeterConversion() throws Exception {
        performConversionTest("FOOT", 1.0, "METER", 0.3048D);
    }

    @Test
    public void testInvalidFeetToMeterConversion() throws Exception {
        performInvalidConversionTest("FEET", -1.0, "METER");
    }

    @Test
    public void testMeterToFeetConversion() throws Exception {
        performConversionTest("METER", 1.0, "FOOT", 3.28084D);
    }

    @Test
    public void testInvalidMeterToFeetConversion() throws Exception {
        performInvalidConversionTest("METER", 1.0D, "FOOT_WRONG_NAME");
    }

    @Test
    public void testKilometerToMileConversion() throws Exception {
        performConversionTest("KILOMETER", 1.0, "MILE", 0.621371D);
    }

    @Test
    public void testInvalidKilometerToMileConversion() throws Exception {
        performInvalidConversionTest("KILOMETER", -1.0, "MILE_WRONG_NAME");
    }

    @Test
    public void testMileToKilometerConversion() throws Exception {
        performConversionTest("MILE", 1.0, "KILOMETER", 1.60934D);
    }

    @Test
    public void testInvalidMileToKilometerConversion() throws Exception {
        performInvalidConversionTest("MILE_WRONG_NAME", -1.0, "KILOMETER");
    }

    @Test
    public void testMillimeterToInchConversion() throws Exception {
        performConversionTest("MILLIMETER", 1.0, "INCH", 0.0393701D);
    }

    @Test
    public void testInvalidMillimeterToInchConversion() throws Exception {
        performInvalidConversionTest("MILLIMETER_WRONG_NAME", null, "INCH");
    }

    @Test
    public void testInchToMillimeterConversion() throws Exception {
        performConversionTest("INCH", 1.0, "MILLIMETER", 25.4);
    }

    @Test
    public void testInvalidInchToMillimeterConversion() throws Exception {
        performInvalidConversionTest("INCH", -1.0, null);
    }

    @Test
    public void testCentimeterToFootConversion() throws Exception {
        performConversionTest("CENTIMETER", 1.0, "FOOT", 0.0328084D);
    }

    @Test
    public void testInvalidCentimeterToFootConversion() throws Exception {
        performInvalidConversionTest("CENTIMETER", -1.0, "foot");
    }

    @Test
    public void testFootToCentimeterConversion() throws Exception {
        performConversionTest("FOOT", 1.0, "CENTIMETER", 30.48D);
    }

    @Test
    public void testInvalidFootToCentimeterConversion() throws Exception {
        performInvalidConversionTest(null, -1.0, null);
    }

    @Test
    public void testMeterToYardConversion() throws Exception {
        performConversionTest("METER", 1.0, "YARD", 1.09361D);
    }

    @Test
    public void testInvalidMeterToYardConversion() throws Exception {
        performInvalidConversionTest("METER", -1.0, null);
    }

    @Test
    public void testYardToMeterConversion() throws Exception {
        performConversionTest("YARD", 1.0, "METER", 0.9144D);
    }

    @Test
    public void testInvalidYardToMeterConversion() throws Exception {
        performInvalidConversionTest(null, -1.0, "METER");
    }

    private void performConversionTest(String fromUnit, double fromValue, String toUnit, double expectedValue) throws Exception {
        CalculationInputParam fromParam = new CalculationInputParam(fromUnit, "CONVERSION_FROM", fromValue);
        CalculationInputParam toParam = new CalculationInputParam(toUnit, "CONVERSION_TO");
        Calculation calculation = new Calculation();
        calculation.setCalculationInputParams(Arrays.asList(fromParam, toParam));

        CalculateRequest calculateRequest = new CalculateRequest();
        calculateRequest.setLinkedFormula(ConversionFormula.GENERIC_SIMPLE_CONVERSION_FORMULA);
        calculateRequest.setCalculationsToPerform(Arrays.asList(calculation));

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/calculation").
                        content(objectMapper.writeValueAsString(calculateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        // Deserialize response content
        Calculation[] calculations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Calculation[].class);
        assertEquals(expectedValue, calculations[0].getOutput(), 0.0001, "Conversion from " + fromUnit + " to " + toUnit + " failed");
    }

    private void performInvalidConversionTest(String fromUnit, Double fromValue, String toUnit) throws Exception {
        CalculationInputParam fromParam = new CalculationInputParam(fromUnit, "CONVERSION_FROM", fromValue);
        CalculationInputParam toParam = new CalculationInputParam(toUnit, "CONVERSION_TO");
        Calculation calculation = new Calculation();
        calculation.setCalculationInputParams(List.of(fromParam, toParam));

        CalculateRequest calculateRequest = new CalculateRequest();
        calculateRequest.setLinkedFormula(ConversionFormula.GENERIC_SIMPLE_CONVERSION_FORMULA);
        calculateRequest.setCalculationsToPerform(List.of(calculation));

        String calculateRequestJson = objectMapper.writeValueAsString(calculateRequest);

        mockMvc.perform(post("/api/v1/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(calculateRequestJson))
                .andExpect(status().isBadRequest());
    }

}