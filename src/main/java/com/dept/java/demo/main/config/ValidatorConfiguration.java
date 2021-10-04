package com.dept.java.demo.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ValidatorConfiguration {

    @Bean
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
