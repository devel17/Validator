/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.service.CodeParser;
import com.dev.validator.backend.exception.InitParseException;
import com.dev.validator.backend.exception.ParseException;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author devel
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class CountryCodeMockParseServiceTest {

    @Mock
    private CodeParser parseService;

    @Test
    @DisplayName("InitParseException")
    public void testInitParseException() throws ParseException {
        when(parseService.init()).thenThrow(new InitParseException(){});
        
        InitParseException thrown = assertThrows(
                InitParseException.class,
                () -> parseService.init(),
                InitParseException.INIT_PARSE_ERROR
        );
        assertTrue(thrown.getMessage().contains(InitParseException.INIT_PARSE_ERROR));
    }
}
