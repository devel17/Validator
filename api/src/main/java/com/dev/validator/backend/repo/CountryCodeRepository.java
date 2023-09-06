/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.backend.repo;

import com.dev.validator.backend.model.CountryCode;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория справочника кодов стран
 *  
 * @author devel
 */
@Repository
public interface CountryCodeRepository extends CrudRepository<CountryCode, Long> {

    List<CountryCode> findByCode(String code);
    
    /**
     * Получение максимальной длины значения поля code
     * @return 
     */
    @Query("select max(length(cc.code)) from CountryCode cc")
    Integer getMaxLenghtCode();

}
