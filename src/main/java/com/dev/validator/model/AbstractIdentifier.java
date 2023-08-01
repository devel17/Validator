/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстракция произвольного идентификатора (UUID, Token , Number, Code ...)
 * 
 * @author devel
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractIdentifier extends Domain {
    
}
