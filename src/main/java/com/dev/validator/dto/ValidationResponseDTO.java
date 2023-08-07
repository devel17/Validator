/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object Ответ процесса валидации
 *
 * @author devel
 */
@Getter
@Setter
public class ValidationResponseDTO {

    public ValidationResponseDTO() {
    }

    public ValidationResponseDTO(String validationResult) {
        this.validationResult = validationResult;
    }

    /**
     * Результат операции
     */
    private String validationResult;

    @Override
    public String toString() {
        return "ValidationResponseDTO{" + "validationResult=" + validationResult + '}';
    }
}
