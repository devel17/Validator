/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.repo.CountryCodeRepository;
import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.exception.ValidateNotFoundException;
import com.dev.validator.backend.model.CountryCode;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author devel
 */
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=none",
    "spring.sql.init.mode=always",
    "spring.sql.init.platform=h2"
})
public class CountryCodeValidateServiceTest {

    private final CountryCodeValidateService countryCodeValidateService;

    @Autowired
    public CountryCodeValidateServiceTest(final CountryCodeRepository repo) {
        this.countryCodeValidateService = new CountryCodeValidateService(repo);
    }

    @Test
    @DisplayName("Test initialization components")
    public void usedComponentsAreNotNull() {
        assertThat(countryCodeValidateService).isNotNull();
    }

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
        assertTrue(thrown.getMessage().contains(ValidateNotFoundException.COUNTRY_NOT_FOUND));

    }
    
    @Test
    @DisplayName("testOverSizeCode")
    public void testOverSizeCode() throws ValidateException {
        ValidateNotFoundException thrown = assertThrows(
                ValidateNotFoundException.class,
                () -> countryCodeValidateService.validate("+12345678987654321"),
                ValidateNotFoundException.COUNTRY_NOT_FOUND
        );
        assertTrue(thrown.getMessage().contains(ValidateNotFoundException.COUNTRY_NOT_FOUND));

    }
    
    @Test
    @DisplayName("testMinSizeCode")
    public void testMinSizeCode() throws ValidateException {
        assertEquals(1, countryCodeValidateService.validate("7").size());
    }

    @Test
    @DisplayName("testPlus")
    public void testPlus() throws ValidateException {
        List<CountryCode> codes = countryCodeValidateService.validate("+71423423412");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Russia");
    }

}
