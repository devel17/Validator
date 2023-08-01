/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.dao.spring;

import com.dev.validator.dao.BaseRepository;
import com.dev.validator.model.CountryCode;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория справочника кодов стран
 *  
 * @author devel
 */
@Repository
public interface CountryCodeRepository extends GenericRepository<CountryCode, Long>, BaseRepository {

    public List<CountryCode> findByCode(String code);

}
