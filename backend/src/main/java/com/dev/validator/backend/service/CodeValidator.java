/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.backend.service;

import com.dev.validator.backend.exception.ValidateException;
import com.dev.validator.backend.model.AbstractCode;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса установки соотвествия
 * 
 * @author devel
 */
public interface CodeValidator {
    
    <T extends AbstractCode> List<T> validate(String src) throws ValidateException;
}
