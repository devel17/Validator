/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object процесса валидации
 * 
 * @author devel
 */
@Getter
@Setter
public class ValidationDTO {
    
    /**
     * Тип соответсвия 
     */
    private String validationType;
    
    /**
     * Источник, для которого необходимо установить соответсвие
     */
    private String validationSource;
    
    /**
     * Результат операции
     */
    private String validationResult;
    
}
