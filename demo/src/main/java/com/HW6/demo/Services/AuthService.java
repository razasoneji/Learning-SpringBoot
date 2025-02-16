package com.HW6.demo.Services;

import com.HW6.demo.Entities.LoginResponse;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void signup(User user) {
        Optional<User> exists = userRepository.findUserByUsername(user.getUsername());
        if(exists.isPresent()) {
            throw new RuntimeException("User Already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public LoginResponse login(String username, String password) {
        if(username.isBlank() || password.isBlank()) {
            throw new RuntimeException("Username or password cannot be empty");
        }
        Optional<User> exists = userRepository.findUserByUsername(username);
        if(exists.isEmpty()){
            throw new RuntimeException("Please Signup First");
        }
        User user = exists.get();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Authentication Failed , unauthenticated user");
        }
        

    }
}
