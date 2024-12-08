package com.firstproject.razasoneji.demoproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;


@Configuration
public class GenderConfig {

    @Bean
    public ArrayList<String> validGenders(){
        return new ArrayList<>(Arrays.asList("male", "female"));
    }

}
