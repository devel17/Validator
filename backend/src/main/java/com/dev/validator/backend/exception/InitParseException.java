/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение инициализации процесса разбора справочника
 *
 * @author devel
 */
@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class InitParseException extends ParseException {

    public static final String INIT_PARSE_ERROR = "Init process parse failed!";

    public InitParseException(String message) {
        super(INIT_PARSE_ERROR + ": " + message);
    }
    
    public InitParseException() {
        super(INIT_PARSE_ERROR);
    }
}
