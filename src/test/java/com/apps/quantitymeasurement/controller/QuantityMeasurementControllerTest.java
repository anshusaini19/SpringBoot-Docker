package com.apps.quantitymeasurement.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void compareAPIShouldReturn200() throws Exception {

        String json = """
        {
          "value":2,
          "unit":"FEET",
          "measurementType":"Length"
        }
        """;

        mockMvc.perform(
                        post("/api/quantity/compare")
                                .queryParam("secondValue","24")
                                .queryParam("secondUnit","INCHES")
                                .queryParam("secondMeasurementType","Length")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/oauth2/authorization/google"));
    }
}