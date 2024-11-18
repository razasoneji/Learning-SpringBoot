package com.firstproject.razasoneji.demoproject;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//by default the scope is singleton , here we want it to be created for every instance new obj ,
// so we write prototype.
@Component
@Scope("prototype")
public class Guava {

    void eatGuava(){
        System.out.println("eatGuava");
    }
}
