package com.project.basicsessionspringsecurity.Services;

import com.project.basicsessionspringsecurity.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(User user) {
        // we get an authentication object. it validates user's input against its email and password.
//        It checks the credentials against the database.
//                If the credentials are correct, Spring Security:
//        Loads the user details using UserDetailsService.
//        Compares the hashed password using the configured PasswordEncoder.
//        Returns an authenticated Authentication object.
//                If authentication fails (wrong password or email not found), an AuthenticationException is thrown.
        // we cannot keep it in the UserService , as authenticationmanager gets the username from userdetails service.

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        User existingUser  = (User) authentication.getPrincipal();

        return jwtService.generateJwt(existingUser);

    }

}
