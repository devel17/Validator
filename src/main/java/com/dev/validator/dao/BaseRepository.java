/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dev.validator.dao;

import com.dev.validator.model.Domain;

/**
 * Абстракция, описывающая поведение произвольного репозитория
 * 
 * @author devel
 * @param <T>
 * @param <ID>
 */
public interface BaseRepository<T extends Domain, ID extends Long> {

}
