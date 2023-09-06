/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.advice;

import com.dev.validator.backend.dto.ResponseDTO;
import com.dev.validator.backend.dto.ValidationResponseDTO;
import com.dev.validator.backend.exception.ParseException;
import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.exception.ValidateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработчик ошибок
 * 
 * @author devel
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ResponseDTO> handleException(ParseException e) {
        ResponseDTO apiError = new ResponseDTO(e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ResponseDTO> handleException(ValidateException e) {
        ResponseDTO apiError = new ResponseDTO(e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ValidateNotFoundException.class)
    public ResponseEntity<Object> handleException(ValidateNotFoundException e) {
        ValidationResponseDTO resp = new ValidationResponseDTO(e.getMessage());
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseDTO apiError = new ResponseDTO("Default exception!");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
