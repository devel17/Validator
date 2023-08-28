/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.exception.InitParseException;
import com.dev.validator.backend.exception.ParseException;
import com.dev.validator.backend.model.AbstractCode;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса разбора справочника
 * 
 * @author devel
 */
public interface CodeParser {

    /**
     * Инициализация процесса разбора справочника 
     * (соединения, прокси, правила и т.д....)
     * 
     * @return 
     * @throws InitParseException 
     */
    Boolean init() throws ParseException;
    
    /**
     * Загрузка данных из источника
     * 
     * @return - тектовое представление в виде списка
     * @throws ParseException 
     */
    List<String> download() throws ParseException;
    
    /**
     * Валидация данных (непечатные символы ...)
     * 
     * @param data
     * @return  
     * @throws ParseException 
     */
    List<String> validate(List<String> data) throws ParseException;
    
    /**
     * Трансформация данных в обьектную модель
     * 
     * @param <T> обьект модели
     * @param data тектовое представление в виде списка
     * @return список обьектов модели
     * @throws ParseException 
     */
    <T extends AbstractCode> List<T> transform(List<String> data) throws ParseException;
    
    
}
