package com.project.auditing.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // generally we get this from the
        // spring auth , where we see wheter it is a user or admin or etc.
        return Optional.of("Normal user:raza");
    }
}
