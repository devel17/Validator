/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ParseException;
import com.dev.validator.model.AbstractIdentifier;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса разбора справочника
 * 
 * @author devel
 */
public interface Parser {

    public <T extends AbstractIdentifier> List<T> parse() throws ParseException;
}
