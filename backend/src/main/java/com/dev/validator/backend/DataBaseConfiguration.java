/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend;

import com.dev.validator.backend.model.Domain;
import com.dev.validator.backend.repo.CountryCodeRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Компонент инициализации настроек для работы с базой данных
 *
 * @author devel
 */
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = CountryCodeRepository.class)
@EntityScan(basePackageClasses = Domain.class)
public class DataBaseConfiguration {

}
