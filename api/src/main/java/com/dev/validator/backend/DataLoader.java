/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend;

import com.dev.validator.backend.exception.ParseException;
import com.dev.validator.backend.model.CountryCode;
import com.dev.validator.backend.repo.CountryCodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.dev.validator.backend.service.CodeParser;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author devel
 */
@Slf4j
@Profile("!test")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CountryCodeRepository repo;

    private final CodeParser parseService;

    /**
     * Инициализация справочника кодов при старте приложения
     *
     * @param args
     */
    @Override
    @Transactional
    public void run(String... args) {
        try {
            parseService.init();
            log.debug("Init load completed!");
            repo.deleteAll();
            log.debug("Delete old data completed!");
            List<String> list = parseService.download();
            log.debug("Download  data completed!");
//            list.stream().forEach(System.out::println);
            list = parseService.validate(list);
            log.debug("Validate data completed!");
//            list.stream().forEach(System.out::println);
            parseService.transform(list).stream()
                    .filter(CountryCode.class::isInstance)
                    .map(CountryCode.class::cast)
//                    .forEach(System.out::println);
                    .forEach(cc -> repo.save(cc));
            log.debug("Transform and save data completed!");
        } catch (ParseException ex) {
            System.exit(0);
        }

    }

}
