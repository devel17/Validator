/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.factory;

import com.dev.validator.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Реализация шаблона "Стратегия" (фабричный метод)
 * 
 * @author devel
 */
@Slf4j
@Component
public class BeanFactoryValidatorService {

    private static final String SERVICE_NAME_SUFFIX = "ValidateService";

    private static BeanFactory beanFactory;

    @Autowired
    public BeanFactoryValidatorService(BeanFactory beanFactory) {
        BeanFactoryValidatorService.beanFactory = beanFactory;
    }

    public static Validator getInstance(String type) {
        log.debug("Set instance by type : " + type);
        return beanFactory.getBean(getValidatorServiceBeanName(type),
                Validator.class);
    }

    private static String getValidatorServiceBeanName(String type) {
        return type + SERVICE_NAME_SUFFIX;
    }
}
