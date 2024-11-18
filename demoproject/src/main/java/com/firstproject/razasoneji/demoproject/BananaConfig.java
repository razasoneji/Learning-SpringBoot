package com.firstproject.razasoneji.demoproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration methods are here , we can declare a configuration bean that returns various instances of bean.
// note that the bean returned inside the configuration like for banana should not be annotated as @component on the banana class
// as it will result in an error that is bean of bean.
// here new keyword used as we need to manage the customized creation.
// in our example we are doing nothing but there could be many things that could be done.

@Configuration
public class BananaConfig {

    @Bean
    public Banana banana() {
        return new Banana();
    }

}
