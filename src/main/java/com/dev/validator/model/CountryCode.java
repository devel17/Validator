/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Сущность для инкапсуляции данных и их обработки спавочника кодов стран
 * 
 * @author devel
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CountryCode extends AbstractCode {

    public CountryCode(String country, String code) {
        this.country = country;
        this.code = code;
    }

    @NonNull
    @Column
    private String country;
    
}
