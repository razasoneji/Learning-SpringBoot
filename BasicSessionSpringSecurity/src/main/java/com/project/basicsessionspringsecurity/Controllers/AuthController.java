package com.project.basicsessionspringsecurity.Controllers;


import com.project.basicsessionspringsecurity.Entities.User;
import com.project.basicsessionspringsecurity.Services.LoginService;
import com.project.basicsessionspringsecurity.Services.SigninService;
import com.project.basicsessionspringsecurity.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final SigninService signinService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<User>  signUpUser(@RequestBody User user){
        User newUser = signinService.signUp(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String>  Login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        String token = loginService.login(user);

        // This is one of the way, other way is to have Authorization Header and in it we can have Bearer token.
        //Cookies are sent automatically, bearer token need to be send explicitly.
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);


        return ResponseEntity.ok(token);


    }

}
