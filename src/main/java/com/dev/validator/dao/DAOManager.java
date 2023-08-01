/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.dao;

import com.dev.validator.model.CountryCode;

/**
 * Абстракция , описывающая интерфейс взаимодействия шаблона Data Access Object
 * 
 * @author devel
 */
public interface DAOManager {
    
    BaseRepository<CountryCode, Long> getCountryCodeRepository();
}
