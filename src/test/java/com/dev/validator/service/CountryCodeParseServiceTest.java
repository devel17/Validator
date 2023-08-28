/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ParseException;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author devel
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 8888)
public class CountryCodeParseServiceTest {

    public static final String WIKIPEDIA_STUB_FILE = "stub.html";
    
    private final CountryCodeParseService parseService;

    @Autowired
    public CountryCodeParseServiceTest(CountryCodeParseService parseService) {
        this.parseService = parseService;
    }

    @BeforeAll
    public static void initDataFromStub() {
        //TODO Add caching stub from wikipedia (expire 1 week/month) parseService.getBody() --> 2File 
        stubFor(get(urlEqualTo("/wiki"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBodyFile(WIKIPEDIA_STUB_FILE)));
    }

    /**
     * Test of parse method, of class CountryCodeParseService.
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Download data from wikipedia stub")
    public void testParse() throws ParseException {
        assertEquals(parseService.download().size(), 544);
    }

    /**
     * Test of transform method, of class CountryCodeParseService.
     *
     * @throws ParseException
     */
    @Test
    public void testTransform() throws ParseException {
        assertEquals(parseService.transform(parseService.download()).size(), 295);
    }
}
