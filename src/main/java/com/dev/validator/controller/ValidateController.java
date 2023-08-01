/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.controller;

import com.dev.validator.factory.BeanFactoryValidatorService;
import com.dev.validator.dto.ValidationDTO;
import com.dev.validator.exception.ValidateException;
import com.dev.validator.model.AbstractIdentifier;
import com.dev.validator.model.Code;
import com.dev.validator.model.CountryCode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер процесса установления соответсвия
 * 
 * @author devel
 */
@RestController
@RequestMapping("/validate")
@Slf4j
public class ValidateController extends BaseController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ValidationDTO byType(@RequestBody ValidationDTO validateDto) throws ValidateException {

        validateService = BeanFactoryValidatorService.getInstance(validateDto.getValidationType());

        List<AbstractIdentifier> result = validateService.validate(validateDto.getValidationSource());
        
        validateDto.setValidationResult(result
                .stream()
                .filter(CountryCode.class::isInstance)
                .map(CountryCode.class::cast)
                .map(CountryCode::getCountry)
                .collect(Collectors.joining(",")));
                
//        validateDto.setValidationResult(result
//                .stream()
//                .filter(Code.class::isInstance)
//                .map(Code.class::cast)
//                .map(Code::getIsNumber)
//                .findFirst()
//                .get()
//                .toString());
                
                

        return validateDto;
    }

}
