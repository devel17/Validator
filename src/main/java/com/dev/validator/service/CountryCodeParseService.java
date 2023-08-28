/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ParseException;
import com.dev.validator.model.AbstractCode;
import com.dev.validator.model.CountryCode;
import com.dev.validator.util.TextContentUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Компонент для чтения и обработки данных справочника кодов
 *
 * @author devel
 */
@Slf4j
@Component
public class CountryCodeParseService implements CodeParser {

    @Value("${validator.url}")
    public String DATA_SOURCE;

//    public String getBody() throws Exception {
//        String result = Jsoup.connect(DATA_SOURCE).execute().body();
//        return result;
//    }
    /**
     * 
     * @throws ParseException 
     */
    @Override
    public void init() throws ParseException {
        log.debug("Data source : " + DATA_SOURCE);
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
                    .map(elem -> TextContentUtils.clean(elem.text().split("UTC")[0]))
                    .filter(elem -> !elem.isEmpty())
                    .collect(Collectors.toList());

            return list;
        } catch (IOException ex) {
            throw new ParseException("Error in parseService.download method : " + ex);
        }
    }

    @Override
    public List<String> validate(List<String> data) throws ParseException {
        data.stream().filter(i -> Collections.frequency(data, i) > 1)
                .collect(Collectors.toSet()).forEach(System.out::println);
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

        for (int i = 0; i < list.size(); i++) {

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
