/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.service;

import com.dev.validator.exception.ValidateException;
import com.dev.validator.model.AbstractIdentifier;
import java.util.List;

/**
 * Абстракция , описывающая поведение и обеспечивающая гарантию реализации процесса установки соотвествия
 * 
 * @author devel
 */
public interface Validator {
    
    public <T extends AbstractIdentifier> List<T> validate(String src) throws ValidateException;
}
