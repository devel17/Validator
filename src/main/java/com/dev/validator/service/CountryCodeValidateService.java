/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.dao.spring.CountryCodeRepository;
import com.dev.validator.dao.spring.SpringDAOManager;
import com.dev.validator.exception.ValidateException;
import com.dev.validator.exception.ValidateNotFoundException;
import com.dev.validator.model.AbstractIdentifier;
import com.dev.validator.model.CountryCode;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация валидатора на основе кода и страны
 *
 * @author devel
 */
@Slf4j
@Service("CountryValidateService")
public class CountryCodeValidateService extends ValidateService {

    @Autowired
    CountryCodeRepository repo;

    @Override
    public <T extends AbstractIdentifier> List<T> validate(String src) throws ValidateException {
        List<CountryCode> result = new ArrayList<>();
        src = src.replaceFirst("[+]", "");

        try {
            for (int i = 1; i < src.length(); i++) {
                if (!src.matches("[0-9]+")) {
                    break;
                }
                String tmp = src.substring(0, i);
                List<CountryCode> list = repo.findByCode(tmp);
                if (!list.isEmpty()) {
                    result = list;
                }
            }
        } catch (Exception ex) {
            throw new ValidateException("Error in CountryCodeValidateService.validate method : " + ex);
        }

        if (result.isEmpty()) {
            throw new ValidateNotFoundException();
        }

        return (List<T>) result;
    }

}
