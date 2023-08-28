/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Абстракция произвольного кода 
 *
 * @author devel
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCode extends Domain {

    @NonNull
    @Column
    public String code;

}
