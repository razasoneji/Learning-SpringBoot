package com.firstproject.razasoneji.demoproject;

// we are creating this class to have it as a bean class.
// we are using the stereotype annotation using the component in order to have it as a bean.

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Apple {

    void eatApple(){
        System.out.println("Method inside the apple is executed");
    }

    @PostConstruct
    void applePostConstruct(){
        System.out.println("Post construct of Apple");
    }

    @PreDestroy
    void applePreDestroy(){
        System.out.println("Pre destroy of Apple");
    }
}
