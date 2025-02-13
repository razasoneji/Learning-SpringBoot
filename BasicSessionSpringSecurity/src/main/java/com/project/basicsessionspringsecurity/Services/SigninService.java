package com.project.basicsessionspringsecurity.Services;

import com.project.basicsessionspringsecurity.Entities.User;
import com.project.basicsessionspringsecurity.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SigninService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new BadCredentialsException("Username already exists"+user.getEmail());
        }
        existingUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser.get());
    }

}
