/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.dao.spring;

import com.dev.validator.model.Domain;
import org.springframework.data.repository.CrudRepository;

/**
 * Базовый Generic репозиторий
 * 
 * @author devel
 * @param <T> - Persistent class extends Domain
 * @param <Long>
 */
public interface GenericRepository<T extends Domain, Long> extends CrudRepository<T, Long> {
    
}
