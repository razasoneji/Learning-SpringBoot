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

    private final SessionService sessionService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository, SessionService sessionService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.sessionService = sessionService;
        this.jwtService = jwtService;
    }

    public void signup(User user) {
        Optional<User> exists = userRepository.findUserByUsername(user.getUsername()); // check if user is already present
        if(exists.isPresent()) {  // if exists throw error.
            throw new RuntimeException("User Already Exists");
        }
        //else encrypt password
        //other busienss logic is possible but we will let it be for now.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //saving the user finally.
        userRepository.save(user);
    }

    public LoginResponse login(String username, String password) {
        if(username.isBlank() || password.isBlank()) { // cannot be blank thing.
            throw new RuntimeException("Username or password cannot be empty");
        }
        Optional<User> exists = userRepository.findUserByUsername(username);
        if(exists.isEmpty()){ // if the user doesnt exist throw error
            throw new RuntimeException("Please Signup First");
        }
        User user = exists.get(); //working on the user that exists.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // username password authentication based on username password.
        if(!authentication.isAuthenticated()){ // ie username or password wrong
            throw new RuntimeException("Authentication Failed , unauthenticated user");
        }
        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        sessionService.generateNewSession(user,refreshToken);

        return new LoginResponse(username, accessToken, refreshToken);
        

    }
}
