/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ParseException;
import com.dev.validator.model.AbstractCode;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса разбора справочника
 * 
 * @author devel
 */
public interface CodeParser {

    /**
     * Инициализация процесса разбора справочника 
     * (соединения, прокси, правила и т.д. ...)
     * 
     * @throws ParseException 
     */
    void init() throws ParseException;
    
    /**
     * Загрузка данных из источника
     * 
     * @return - тектовое представление в виде списка
     * @throws ParseException 
     */
    List<String> download() throws ParseException;
    
    /**
     * Валидация данных
     * 
     * @param data
     * @return  
     * @throws ParseException 
     */
    List<String> validate(List<String> data) throws ParseException;
    
    /**
     * Трансформация данных в обьектную модель
     * 
     * @param <T> обьект предметной области
     * @param data 
     * @return
     * @throws ParseException 
     */
    <T extends AbstractCode> List<T> transform(List<String> data) throws ParseException;
    
    
}
