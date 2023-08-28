/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.integration;

import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.model.CountryCode;
import com.dev.validator.backend.repo.CountryCodeRepository;
import com.dev.validator.backend.service.CodeValidator;
import com.dev.validator.backend.service.CountryCodeValidateService;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author devel
 */
@DataJpaTest
@ActiveProfiles("integration")
public class IntegrationTest {

    private final CountryCodeRepository repo;

    private final CodeValidator validator;
    
    @Autowired
    public IntegrationTest(CountryCodeRepository repo) {
        this.repo = repo;
        this.validator = new CountryCodeValidateService(repo);
    }

    @Test
    public void isComponentNotNull() {
        assertNotNull(this.repo);
        assertNotNull(this.validator);
    }

    @Test
    @Sql("classpath:/data-mysql.sql")
    public void testBelarusNumberRepo() {
        List<CountryCode> codes = repo.findByCode("375");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Belarus");
    }

    @Test
    @Sql("classpath:/data-mysql.sql")
    public void testBelarusNumberService() throws ValidateException {
        List<CountryCode> codes = validator.validate("375291112233");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Belarus");
    }
}
