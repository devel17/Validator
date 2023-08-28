/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.repo.CountryCodeRepository;
import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.exception.ValidateNotFoundException;
import com.dev.validator.backend.model.AbstractCode;
import com.dev.validator.backend.model.CountryCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Реализация валидатора на основе кода и страны
 *
 * @author devel
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CountryCodeValidateService implements CodeValidator {

    private final CountryCodeRepository repo;

    @Override
    public <T extends AbstractCode> List<T> validate(String src) throws ValidateException {
        List<CountryCode> result = new ArrayList<>();

        src = src.replaceFirst("[+]", "");
        if (src.isEmpty() || !src.matches("[0-9]+") || src.length() > 15) {
            throw new ValidateNotFoundException();
        }

        try {
            for (int i = 1; i <= Math.min(repo.getMaxLenghtCode(), src.length()); i++) {
                List<CountryCode> list = repo.findByCode(src.substring(0, i));
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
