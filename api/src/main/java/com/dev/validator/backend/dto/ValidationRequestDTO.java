/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Data Transfer Object запрос процесса валидации
 *
 * @author devel
 */
@Data
@Jacksonized
@Builder
@AllArgsConstructor
public class ValidationRequestDTO {

    /**
     * Источник, для которого необходимо установить соответсвие
     */
    private String validationSource;

}
