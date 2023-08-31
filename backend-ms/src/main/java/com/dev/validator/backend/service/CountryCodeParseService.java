/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.exception.InitParseException;
import com.dev.validator.backend.exception.ParseException;
import com.dev.validator.backend.model.AbstractCode;
import com.dev.validator.backend.model.CountryCode;
import com.dev.validator.backend.util.TextContentUtils;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Реализация компонента для чтения и обработки данных справочника кодов
 *
 * @author devel
 */
@Slf4j
@Component
public class CountryCodeParseService implements CodeParser {

    @Value("${validator.url}")
    public String DATA_SOURCE;

//    public String getBody() throws Exception {
//        return Jsoup.connect(DATA_SOURCE).execute().body();
//    }
    @Override
    public Boolean init() throws ParseException {
        log.debug("Data source : " + DATA_SOURCE);
        try {
            Jsoup.connect(DATA_SOURCE).get();
        } catch (UnknownHostException ex) {
            log.error("Check internet or network connection : " + ex);
            throw new InitParseException("Error in CountryCodeParseService init method: " + ex);
        } catch (IOException ex) {
            throw new ParseException("Error in CountryCodeParseService init method: " + ex);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<String> download() throws ParseException {
        Document doc;
        try {
            doc = Jsoup.connect(DATA_SOURCE).get();

            Elements table = doc.select("h2+table.wikitable");

            List<String> list = table
                    .select("tr").select("td")
                    .stream()
                    .map(elem -> elem.text().split("UTC")[0])
                    .filter(elem -> !elem.isEmpty())
                    .collect(Collectors.toList());

            return list;
        } catch (IOException ex) {
            throw new ParseException("Error in CountryCodeParseService download method : " + ex);
        }
    }

    @Override
    public List<String> validate(List<String> data) throws ParseException {
        data = data.stream()
                .map(elem -> TextContentUtils.deleteCharByCodePoint(elem, 160))
                .collect(Collectors.toList());
//        data = data.stream()
//                .distinct()
//                .collect(Collectors.toList());
        return data;
    }

    /**
     * Трансформация данных
     *
     * @param <T>
     * @param list
     * @return
     * @throws ParseException
     */
    @Override
    public <T extends AbstractCode> List<T> transform(List<String> list) throws ParseException {

        List<CountryCode> rez = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {

            if (!list.get(i + 1).contains("(")) {
                if (!list.get(i + 1).contains(",")) {
                    //simple single code like 7; 375 ...
                    rez.add(new CountryCode(list.get(i).trim(), list.get(i + 1)));
                } else {
                    // process like International Networks	882, 883
                    for (String cc : list.get(i + 1).split(",")) {
                        rez.add(new CountryCode(list.get(i).trim(), cc.trim()));
                    }
                }
            } else {
                if (!list.get(i + 1).contains(",")) {
                    //single code with main part like 358 (18); 1 (684) ...
                    rez.add(new CountryCode(list.get(i).trim(), list.get(i + 1).replaceAll("[^\\d]", "")));
                } else {
                    //several codes with main part like 599 (3,4,7) ; 1 (809, 829, 849) ...
                    //Kazakhstan 7 (6, 7) (997 assigned but unused) 
                    //Vatican   39 (06698),assigned 379
                    //Sint Maarten (Netherlands)	1 (721)

                    String mainPart = list.get(i + 1).split("\\(")[0].trim(); //remember main part of code
                    String str = list.get(i + 1).substring(mainPart.length() + 1).trim(); // delete main part from str
                    str = str.replaceAll("[\\(\\)A-Za-z]", "").trim(); // delete symbols )(a-z A-z
                    str = str.replaceAll("[\\,\\  ]", " ").trim(); // replace symbols \\, \\spacespace  to \\space 

                    for (String tmp : str.split(" ")) {
                        if (tmp.length() != 0) {
                            rez.add(new CountryCode(list.get(i).trim(), mainPart + tmp.trim())); //process region code
                        }
                    }
                }
            }
            i++;
        }
        return (List<T>) rez;
    }

}
