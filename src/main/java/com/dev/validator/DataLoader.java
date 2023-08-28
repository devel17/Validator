/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator;

import com.dev.validator.exception.ParseException;
import com.dev.validator.model.CountryCode;
import com.dev.validator.repo.CountryCodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.dev.validator.service.CodeParser;

/**
 *
 * @author devel
 */
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
     * @throws ParseException
     */
    @Override
    @Transactional
    public void run(String... args) throws ParseException {
        this.repo.deleteAll();
        parseService.init();
        List<String> list = parseService.download();
        list = parseService.validate(list);
        parseService.transform(list).stream()
                .filter(CountryCode.class::isInstance)
                .map(CountryCode.class::cast)
                .forEach(cc -> repo.save(cc));
    }

}
