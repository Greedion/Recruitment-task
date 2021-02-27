package com.task.dataaccessobject;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "path.java")
@Getter
@Setter
class ConfigurationPropertiesValue {
    private  String female;
    private String  male;

}
