/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.advice;

import com.dev.validator.dto.ResponseDTO;
import com.dev.validator.dto.ValidationResponseDTO;
import com.dev.validator.exception.ValidateException;
import com.dev.validator.exception.ValidateNotFoundException;
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

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ResponseDTO> handleException(ValidateException e) {
        ResponseDTO responseDto = new ResponseDTO(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    @ExceptionHandler(ValidateNotFoundException.class)
    public ResponseEntity<ValidationResponseDTO> handleException(ValidateNotFoundException e) {
        ValidationResponseDTO validationResponseDTO = new ValidationResponseDTO(e.getMessage());
        return new ResponseEntity<>(validationResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseDTO apiError = new ResponseDTO("Default exception");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
