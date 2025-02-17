package com.HW6.demo.Services;

import com.HW6.demo.Entities.LoginResponse;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        if(username.isBlank() || password.isBlank()) { // cannot be blank thing.
            throw new RuntimeException("Username or password cannot be empty");
        }
        Optional<User> exists = userRepository.findUserByUsername(username);
        if(exists.isEmpty()){ // if the user doesnt exist
            throw new RuntimeException("Please Signup First");
        }
        User user = exists.get(); //working on the user.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // username password authentication based on username password.
        if(!authentication.isAuthenticated()){ // ie username or password wrong
            throw new RuntimeException("Authentication Failed , unauthenticated user");
        }
        LoginResponse.
        

    }
}
