/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.dao.spring.CountryCodeRepository;
import com.dev.validator.dao.spring.SpringDAOManager;
import com.dev.validator.exception.ParseException;
import com.dev.validator.exception.ValidateException;
import com.dev.validator.exception.ValidateNotFoundException;
import com.dev.validator.model.CountryCode;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author devel
 */
@DataJpaTest
//@TestInstance(Lifecycle.PER_CLASS)
//@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CountryCodeValidateServiceTest {

//    @Mock
    @Autowired
    private CountryCodeRepository repo;

//    @InjectMocks
    @Autowired
    private CountryCodeValidateService countryCodeValidateService;

//    @InjectMocks 
//    private ParseService parseService;
//    @Autowired
//    TestEntityManager testEntityManager;
//    @BeforeAll
//    public void initData() throws ParseException {
//        parseService.parse().stream()
//                .filter(CountryCode.class::isInstance)
//                .map(CountryCode.class::cast)
//                .forEach(cc -> testEntityManager.persist(cc));
//    }
    @Test
    @DisplayName("testBahamas")
    public void testBahamas() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("12423222931");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Bahamas");
    }

    @Test
    @DisplayName("testUSACanada")
    public void testUSACanada() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("11165384765");
        assertThat(codes).extracting(CountryCode::getCountry).contains("United States", "Canada");
    }

    @Test
    @DisplayName("testRussia")
    public void testRussia() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("71423423412");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Russia");
//        assertEquals(codes.stream().map(CountryCode::getCountry).collect(Collectors.joining(",")), "Russia");

    }

    @Test
    @DisplayName("testKazakhstan")
    public void testKazakhstan() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("77112227231");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Kazakhstan");
    }

    @Test
    @DisplayName("testNotFound")
    public void testNotFound() throws ValidateException {
        ValidateNotFoundException thrown = assertThrows(
                ValidateNotFoundException.class,
                () -> countryCodeValidateService.validate("000000000000"),
                ValidateNotFoundException.COUNTRY_NOT_FOUND
        );
//        Assertions.assertEquals(ValidateNotFoundException.COUNTRY_NOT_FOUND, thrown.getMessage());
        assertTrue(thrown.getMessage().contains(ValidateNotFoundException.COUNTRY_NOT_FOUND));

    }
    
    
    @Test
    @DisplayName("testLiteralCode")
    public void testLiteralCode() throws ValidateException {
        ValidateNotFoundException thrown = assertThrows(
                ValidateNotFoundException.class,
                () -> countryCodeValidateService.validate("ssadsadsa"),
                ValidateNotFoundException.COUNTRY_NOT_FOUND
        );
//        Assertions.assertEquals(ValidateNotFoundException.COUNTRY_NOT_FOUND, thrown.getMessage());
        assertTrue(thrown.getMessage().contains(ValidateNotFoundException.COUNTRY_NOT_FOUND));

    }
    
    @Test
    @DisplayName("testPlus")
    public void testPlus() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("+71423423412");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Russia");
//        assertEquals(codes.stream().map(CountryCode::getCountry).collect(Collectors.joining(",")), "Russia");

    }

}
