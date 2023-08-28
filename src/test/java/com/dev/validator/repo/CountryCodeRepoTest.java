/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.repo;

import com.dev.validator.model.CountryCode;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author devel
 */
@DataJpaTest
@ActiveProfiles("test")
public class CountryCodeRepoTest {

    public static final String BELARUS_COUNTRY_NAME = "Belarus";

    public static final String BELARUS_COUNTRY_FULL_NAME = "Republic of Belarus";

    public static final String BELARUS_COUNTRY_CODE = "375";

    private final TestEntityManager entityManager;

    private final CountryCodeRepository repo;

    @Autowired
    public CountryCodeRepoTest(TestEntityManager entityManager, CountryCodeRepository repo) {
        this.entityManager = entityManager;
        this.repo = repo;
    }

    @BeforeEach
    public void createBelarusCountryCode() throws Exception {
        CountryCode cc = new CountryCode(BELARUS_COUNTRY_NAME, BELARUS_COUNTRY_CODE);
        entityManager.persist(cc);
    }

    @Test
    @DisplayName("readBelarusCountryCode")
    public void readBelarusCountryCode() throws Exception {
        List<CountryCode> codes = repo.findByCode(BELARUS_COUNTRY_CODE);
        assertThat(codes).extracting(CountryCode::getCode).contains(BELARUS_COUNTRY_CODE);
        assertThat(codes).extracting(CountryCode::getCountry).contains(BELARUS_COUNTRY_NAME);
    }

    @Test
    @DisplayName("updateBelarusCountryCode")
    public void updateBelarusCountryCode() throws Exception {
        CountryCode cc = repo.findByCode(BELARUS_COUNTRY_CODE).get(0);
        cc.setCountry(BELARUS_COUNTRY_FULL_NAME);
        entityManager.persistAndFlush(cc);
        List<CountryCode> codes = repo.findByCode(BELARUS_COUNTRY_CODE);
        assertThat(codes).extracting(CountryCode::getCountry).contains(BELARUS_COUNTRY_FULL_NAME);
    }

    @Test
    @DisplayName("deleteBelarusCountryCode")
    public void deleteBelarusCountryCode() throws Exception {
        CountryCode cc = repo.findByCode(BELARUS_COUNTRY_CODE).get(0);
        entityManager.remove(cc);

        List<CountryCode> codes = repo.findByCode(BELARUS_COUNTRY_CODE);
        assertTrue(codes.isEmpty());
    }
    
    @Test
    @DisplayName("getting max length value of code's field")
    public void getMaxLength() throws Exception {
        Integer maxLen = repo.getMaxLenghtCode();
        Assertions.assertEquals(maxLen, Integer.valueOf(3));
    }

}
