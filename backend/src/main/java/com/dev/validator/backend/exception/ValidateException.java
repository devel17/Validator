/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Базовое исключение процесса установки соответсвия
 * 
 * @author devel
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ValidateException extends Exception {

    public ValidateException(String message) {
        super(message);
    }
    
}
