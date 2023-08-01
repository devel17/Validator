package com.dev.validator.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import com.dev.validator.dao.DAOManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Реализация по умолчанию шаблона Data Access Object посредством Spring (чтение из базы данных)
 * 
 * @author devel
 */
@Primary
public class SpringDAOManager implements DAOManager {
    
    @Autowired
    RepositoryFactorySupport rfs;
    
    @Override
    public CountryCodeRepository getCountryCodeRepository() {
        return rfs.getRepository(CountryCodeRepository.class);
    }
    
}
