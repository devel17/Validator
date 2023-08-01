/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ValidateException;
import com.dev.validator.model.AbstractIdentifier;
import com.dev.validator.model.Code;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Реализация простейшего валидатора числового значения
 * 
 * @author devel
 */
@Service("NumberValidateService")
public class ValidateService extends BaseService implements Validator {

    @Override
    public <T extends AbstractIdentifier> List<T> validate(String src) throws ValidateException {
        List<Code> result = new ArrayList<>();
        try {
            if (src.matches("[0-9]+")) {
                Code code = new Code();
                code.setIsNumber(Boolean.TRUE);
                result.add(code);
            }
        } catch (Exception ex) {
            throw new ValidateException("Error in ValidateService.validate method : " + ex);
        }
        return (List<T>) result;
    }

}
