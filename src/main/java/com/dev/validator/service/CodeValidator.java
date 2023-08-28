/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ValidateException;
import com.dev.validator.model.AbstractCode;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса установки соотвествия
 * 
 * @author devel
 */
public interface CodeValidator {
    
    <T extends AbstractCode> List<T> validate(String src) throws ValidateException;
}
