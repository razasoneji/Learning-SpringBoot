package com.firstproject.razasoneji.demoproject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env",havingValue = "production")
public class MockProdDB implements CONFIGDB{
    @Override
    public String printName() {
        return "Inside MockProdDB";
    }
}
