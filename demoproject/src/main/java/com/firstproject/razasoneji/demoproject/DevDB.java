package com.firstproject.razasoneji.demoproject;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DevDB implements DB{

    @Override
    public String getmockdata() {
        return "Development Database";
    }
}
