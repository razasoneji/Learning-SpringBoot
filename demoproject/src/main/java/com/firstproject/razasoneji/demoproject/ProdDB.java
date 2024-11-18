package com.firstproject.razasoneji.demoproject;

import org.springframework.stereotype.Component;

@Component
public class ProdDB implements DB {


    @Override
    public String getmockdata() {
        return "Production Database";
    }
}
