package com.task;


import com.task.dataaccessobject.DataAccessFactory;
import logic.Algorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RecruitmentTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentTaskApplication.class, args);
    }
    @Bean
    Algorithm getAlgorithm(DataAccessFactory dataAccessFactory){
        return new Algorithm(dataAccessFactory);
    }


}
