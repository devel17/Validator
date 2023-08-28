package com.dev.validator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Data Transfer Object обработчика ошибок
 *
 * @author devel
 */
@Data
@AllArgsConstructor
public class ResponseDTO {

    private String message;

}
