/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.controller;

import com.dev.validator.backend.dto.ValidationRequestDTO;
import com.dev.validator.backend.model.AbstractCode;
import com.dev.validator.backend.model.CountryCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.dev.validator.backend.service.CodeValidator;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Тестирование WEB слоя
 *
 * @author devel
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ValidateControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private CodeValidator validateService;

    @Autowired
    public ValidateControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testGet() throws Exception {
        ValidationRequestDTO req = new ValidationRequestDTO("375291112233");

        List<AbstractCode> codes = new ArrayList<>(
                Arrays.asList(
                        new CountryCode("Belarus", "375")
                ));

        when(validateService.validate(req.getValidationSource())).thenReturn(codes);
        
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("phone", req.getValidationSource());

        mockMvc.perform(get("/validate").params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(codes.size()))
                .andDo(print());
    }

    @Test
    public void testPost() throws Exception {
        ValidationRequestDTO req = new ValidationRequestDTO("375291112233");

        List<AbstractCode> mockList = new ArrayList<>(
                Arrays.asList(
                        new CountryCode("Belarus", "375")
                ));

        when(validateService.validate(req.getValidationSource())).thenReturn(mockList);

        mockMvc.perform(post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(print())                
                .andExpect(status().isOk());
    }
}
