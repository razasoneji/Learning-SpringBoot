package com.firstproject.razasoneji.demoproject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env",havingValue  = "development")
public class MockDevDB implements  CONFIGDB{

    @Override
    public String printName() {
        return "Inside the MockDevDB ";
    }
}
