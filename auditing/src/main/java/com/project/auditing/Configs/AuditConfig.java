package com.project.auditing.Configs;

import com.project.auditing.auth.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditConfig {
    @Bean
    public AuditorAware<String> getAuditorAwareImpl() {
        return new AuditAwareImpl();
    }
}
