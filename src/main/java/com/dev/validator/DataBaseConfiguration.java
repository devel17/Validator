/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator;

import com.dev.validator.dao.spring.SpringDAOManager;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Компонент инициализации настроек для работы с базой данных
 * @author devel
 */
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories("com.dev.validator.dao")
@EntityScan({"com.dev.validator.model"})
public class DataBaseConfiguration {

    @Bean
    SpringDAOManager daoManager() {
        return new SpringDAOManager();
    }

    @Bean(name = "rfs")
    RepositoryFactorySupport getRFS(EntityManager em) {
        return new JpaRepositoryFactory(em);
    }

}
