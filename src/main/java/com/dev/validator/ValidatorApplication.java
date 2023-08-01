package com.dev.validator;

import com.dev.validator.dao.spring.SpringDAOManager;
import com.dev.validator.exception.ParseException;
import com.dev.validator.model.CountryCode;
import com.dev.validator.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.dev.validator"})
@Import({DataBaseConfiguration.class})
public class ValidatorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ValidatorApplication.class, args);
    }

    @Autowired
    ParseService parseService;

    @Autowired
    SpringDAOManager daoManager;

    /**
     * Инициализация справочника кодов при старте приложения
     * @throws ParseException 
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initCountryCodeReference() throws ParseException {
        daoManager.getCountryCodeRepository().deleteAll();
        parseService.parse().stream()
                .filter(CountryCode.class::isInstance)
                .map(CountryCode.class::cast)
                .forEach(cc -> daoManager.getCountryCodeRepository().save(cc));
    }

}
