/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.exception;

/**
 * Исключение отсутствия соответсвия
 *
 * @author devel
 */
public class ValidateNotFoundException extends ValidateException {

    public static final String COUNTRY_NOT_FOUND = "Country not found!";

    public ValidateNotFoundException() {
        super(COUNTRY_NOT_FOUND);
    }

}
