package com.firstproject.razasoneji.demoproject;

//This class will be inside a configuration class and made a bean there.

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Banana {

    void eatBanana() {
        System.out.println("Inside the eatBanana method");
    }

    @PostConstruct
    void showAfterBeanConstructAndInitz(){
        System.out.println("Inside the PostConstruct of Banana");
    }

    @PreDestroy
    void showAfterBeanDestroy(){
        System.out.println("Inside the PreDestroy of Banana");
    }
}
