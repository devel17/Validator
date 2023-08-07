/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение отсутствия соответсвия
 *
 * @author devel
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidateNotFoundException extends ValidateException {

    public static final String COUNTRY_NOT_FOUND = "Country not found!";

    public ValidateNotFoundException() {
        super(COUNTRY_NOT_FOUND);
    }

}
