/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.dao.spring.CountryCodeRepository;
import com.dev.validator.dao.spring.SpringDAOManager;
import com.dev.validator.exception.ParseException;
import com.dev.validator.model.CountryCode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author devel
 */
@SpringBootTest
public class ParseServiceTest {
    
    @InjectMocks 
    private ParseService parseService;
     
    @DisplayName("testConsistentData")
    @Test
    public void testConsistentData() throws ParseException {
        assertEquals(parseService.parse().size(), 295);
    }
}
