/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Data Transfer Object Ответ процесса валидации
 *
 * @author devel
 */
@Data
@Jacksonized
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponseDTO {

    /**
     * Результат операции
     */
    private String validationResult;
}
