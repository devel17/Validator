/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстракиция произвольного кода (подразделения, доступа, числового ...)
 * 
 * @author devel
 */
@Getter
@Setter
@MappedSuperclass
public class Code extends AbstractIdentifier {
    
    @Column
    public String code;
    
    @Transient
    public Boolean isNumber;
    
}
