/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.controller;

import com.dev.validator.backend.dto.ValidationRequestDTO;
import com.dev.validator.backend.dto.ValidationResponseDTO;
import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.model.AbstractCode;
import com.dev.validator.backend.model.CountryCode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dev.validator.backend.service.CodeValidator;

/**
 * Контроллер процесса установления соответсвия
 *
 * @author devel
 */
@Slf4j
@RestController
@RequestMapping("/validate")
@RequiredArgsConstructor
public class ValidateController {

    private final CodeValidator validateService;

    @PostMapping
    public ValidationResponseDTO post(@RequestBody ValidationRequestDTO validationRequestDTO) throws ValidateException {
        log.debug("Request : " + validationRequestDTO.toString());
        List<AbstractCode> result = new ArrayList<>();
        if (!validationRequestDTO.getValidationSource().isEmpty()) {
            result = validateService.validate(validationRequestDTO.getValidationSource());
        }

        ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO();
        validationResponseDTO.setValidationResult(result
                .stream()
                .filter(CountryCode.class::isInstance)
                .map(CountryCode.class::cast)
                .map(CountryCode::getCountry)
                .collect(Collectors.joining(", ")));

        log.debug("Response : " + validationResponseDTO.toString());
        return validationResponseDTO;
    }

    @GetMapping
    public ResponseEntity<List<CountryCode>> get(@RequestParam(required = false) String phone) throws ValidateException {
        List<CountryCode> codes = new ArrayList<>();
        if (phone != null) {
            validateService.validate(phone)
                    .stream()
                    .filter(CountryCode.class::isInstance)
                    .map(CountryCode.class::cast)
                    .forEach(codes::add);
        }
        return new ResponseEntity<>(codes, HttpStatus.OK);
    }

}
