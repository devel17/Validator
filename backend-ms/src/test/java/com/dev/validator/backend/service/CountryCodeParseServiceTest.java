/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.exception.ParseException;
import com.dev.validator.backend.model.CountryCode;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
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

    private final CodeParser parseService;

    @Autowired
    public CountryCodeParseServiceTest(CodeParser parseService) {
        this.parseService = parseService;
    }

    @BeforeAll
    public static void initDataFromStub() {
        //TODO Add caching stub from wikipedia (expire 1 day/week) parseService.getBody() --> 2File 
        stubFor(get(urlEqualTo("/wiki"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBodyFile(WIKIPEDIA_STUB_FILE)));
    }
    
    @AfterEach
    public void devideTest() {
        System.out.println();
    }

    @Test
    @DisplayName("Download data from wikipedia")
    public void testDownload() throws ParseException {
        assertEquals(parseService.download().size(), 544);
    }

    @Test
    @DisplayName("Test delete special symbol")
    public void testValidateAland() throws ParseException {
        List<String> listOnlyAlandOrig = parseService.download().stream().filter(i -> i.contains("Åland")).collect(Collectors.toList());
        printPairCodeChar(!listOnlyAlandOrig.isEmpty() ? listOnlyAlandOrig.get(0) : "");
        assertThat(listOnlyAlandOrig).contains(new StringBuilder().appendCodePoint(160).append("Åland").toString());

        List<String> listOnlyAlandValidated = parseService.validate(listOnlyAlandOrig);
        assertThat(listOnlyAlandValidated).contains("Åland");
    }

    @Test
    @DisplayName("Test delete several special symbols")
    public void testValidateNepal() throws ParseException {
        List<String> listOrig = parseService.download().stream().filter(i -> i.contains("Nepal")).collect(Collectors.toList());
        printPairCodeChar(!listOrig.isEmpty() ? listOrig.get(0) : "");
        assertThat(listOrig).contains(new StringBuilder().appendCodePoint(160).appendCodePoint(160).appendCodePoint(160).append("Nepal").toString());

        List<String> listValidated = parseService.validate(listOrig);
        assertThat(listValidated).contains("Nepal");
    }

    /**
     * Печатает пары [код символа - символ] для значения
     *
     * @param list
     */
    private void printPairCodeChar(String text) {
        text.codePoints().forEach(c -> System.out.println(c + " - " + new StringBuilder().appendCodePoint(c)));
    }

    @Test
    @DisplayName("Test transform all")
    public void testTransformAll() throws ParseException {
        assertEquals(parseService.transform(parseService.download()).size(), 295);
    }

    /**
     * Example : 7; 375 ...
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Simple single code")
    public void testTransformSimpleSingle() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Belarus",
                        "375"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 1);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("375");
    }

    /**
     * Example : 882, 883, ...
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Simple enumeration")
    public void testTransformEnum() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "International Networks",
                        "882, 883"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 2);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("882","883");
    }

    /**
     * Example : 358 (18); 1 (684) ...
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Single code with main part")
    public void testTransformSingleMainPart() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Aland",
                        "358 (18)"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 1);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("35818");
    }

    /**
     * Example : 599 (3,4,7) ; 1 (809, 829, 849) ...
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Several codes with main part")
    public void testTransformSeveral() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Caribbean Netherlands",
                        "599 (3,4,7)"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 3);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("5993","5994","5997");
    }

    /**
     * Example : Kazakhstan 7 (6, 7) (997 assigned but unused)
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Test Kazakhstan")
    public void testTransformKazakhstan() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Kazakhstan",
                        "7 (6, 7) (997 assigned but unused)"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 3);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("76","77","7997");
    }

    /**
     * Example : Vatican 39 (06698),assigned 379
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Test Vatican")
    public void testTransformVatican() throws ParseException {
         List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Vatican",
                        "39 (06698),assigned 379"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 2);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("3906698","39379");
    }

    /**
     * Example : Sint Maarten (Netherlands)	1 (721)
     *
     * @throws ParseException
     */
    @Test
    @DisplayName("Test Sint Maarten")
    public void testTransformSintMaarten() throws ParseException {
        List<String> listOrig = new ArrayList<>(
                Arrays.asList(
                        "Sint Maarten",
                        "1 (721)"
                ));
        List<CountryCode> listTransformed = parseService.transform(listOrig);
        listTransformed.stream().forEach(System.out::println);
        assertEquals(listTransformed.size(), 1);
        assertThat(listTransformed).extracting(CountryCode::getCode).contains("1721");
    }
}
