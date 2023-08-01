/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность для инкапсуляции данных и их обработки спавочника кодов стран
 * 
 * @author devel
 */
@Getter
@Setter
@Entity
public class CountryCode extends Code {

    public CountryCode() {
    }

    
    public CountryCode(String country, String code) {
        this.code = code;
        this.country = country;
    }
    
    @Column
    private String country;
    
}
