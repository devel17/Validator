/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.dao.spring.SpringDAOManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Базовый сервис для хранения общих компонентов
 * 
 * @author devel
 */
public abstract class BaseService {
    
    @Autowired
    SpringDAOManager daoManager;
}
