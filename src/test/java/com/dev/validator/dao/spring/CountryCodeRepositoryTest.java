/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.dao.spring;

import com.dev.validator.model.CountryCode;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 *
 * @author devel
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CountryCodeRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private CountryCodeRepository repository;
    
    @Test
    @DisplayName("getBy")
    public void getBy() throws Exception {
        List<CountryCode> codes = repository.findByCode("375");
        assertThat(codes).extracting(CountryCode::getCode).contains("375");
        assertThat(codes).extracting(CountryCode::getCountry).contains("Belarus");
    }
}
