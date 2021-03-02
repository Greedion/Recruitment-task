package com.task.configuration;

import logic.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import logic.DataAccessInterface;

@Configuration
class BeanFactory {

    @Bean
    Algorithm getAlgorithm(DataAccessInterface dataAccessFactory){
        return new Algorithm(dataAccessFactory);
    }

}
