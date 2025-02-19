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
import java.util.logging.Logger;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final SessionService sessionService;
    private final JwtService jwtService;

    private static final Logger log = Logger.getLogger(AuthService.class.getName());

    @Autowired
    public AuthService(UserRepository userRepository, SessionService sessionService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.sessionService = sessionService;
        this.jwtService = jwtService;
    }

    public void signup(User user) {
        log.info("Call received to the signup in AuthService: " + user.toString());
        Optional<User> exists = userRepository.findUserByUsername(user.getUsername()); // check if user is already present
        if(exists.isPresent()) {// if exists throw error.
            log.info("signup in AuthService , Username already exists: " + user.getUsername());
            throw new RuntimeException("User Already Exists");
        }
        //else encrypt password
        //other business logic is possible but we will let it be for now.
        log.info("signup in AuthService , Creating new User: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //saving the user finally.
        log.info("Finally going to save user. User: " + user.getUsername());
        userRepository.save(user);
        log.info("User saved successfully user: " + user.getUsername());
    }

    public LoginResponse login(String username, String password) {
        log.info("Call received to the login in AuthService: " + username);
        if(username.isBlank() || password.isBlank()) {// cannot be blank thing.
            log.info("Login in AuthService , Username or Password is empty");
            throw new RuntimeException("Username or password cannot be empty");
        }
        log.info("Login in AuthService , Finding user in repo : " + username);
        Optional<User> exists = userRepository.findUserByUsername(username);
        if(exists.isEmpty()){// if the user doesn't exist throw error
            log.info("Login in AuthService , Username not found: " + username);
            throw new RuntimeException("Please Signup First");
        }
        log.info("Login in AuthService , User exists : " + username);
        User user = exists.get(); //working on the user that exists.
        log.info("Pre Authentication for user: " + username);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // username password authentication based on username password.
        if(!authentication.isAuthenticated()){// ie username or password wrong
            log.info("User is not authenticated: " + username);
            throw new RuntimeException("Authentication Failed , unauthenticated user");
        }
        log.info("User is authenticated: " + username);
        log.info("Will generate jwt access token for user: " + username);
        String accessToken = jwtService.generateAccessToken(username);
        log.info("Will generate jwt refresh token for user: " + username);
        String refreshToken = jwtService.generateRefreshToken(username);

        log.info("Generated jwt AT,RT for user: " + username);
        log.info("Will generate Session for user : " + username);
        sessionService.generateNewSession(user,refreshToken);
        log.info("Generated Session for user : " + username);

        log.info("Will return LoginResponse for user: " + username);
        return new LoginResponse(username, accessToken, refreshToken);
    }

    public LoginResponse refresh(String refreshToken) {
        log.info("Call received to refresh in AuthService: " + refreshToken);
        if(!sessionService.exists(refreshToken)){
            log.info("Refresh token NOT found in database : " + refreshToken);
            throw new RuntimeException("Refresh token NOT found in database : " + refreshToken);
        }
        log.info("Refresh token found in database : " + refreshToken);
        log.info("Getting user by refresh token");
        User user = sessionService.getUserByRefreshToken(refreshToken);
        log.info("RT AT rotation will occur now");
        sessionService.deleteByRefreshToken(refreshToken);
        log.info("Refresh token deleted and now new will be set");
        log.info("Will generate jwt access token for user: " + user.getUsername());
        String accessToken = jwtService.generateAccessToken(user.getUsername());
        log.info("Will generate jwt refresh token for user: " + user.getUsername());
        String newRefreshToken = jwtService.generateRefreshToken(user.getUsername());
        log.info("Generated jwt AT,RT for user: " + user.getUsername());
        sessionService.generateNewSession(user,newRefreshToken);
        log.info("Generated Session for user : " + user.getUsername());
        log.info("Will return LoginResponse for user: " + user.getUsername());
        return new LoginResponse(user.getUsername(), accessToken, newRefreshToken);


    }

    public void deleteByRefreshToken(String refreshToken) {
        sessionService.deleteByRefreshToken(refreshToken);
    }
}
