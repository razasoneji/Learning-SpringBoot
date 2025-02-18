package com.HW6.demo.Controllers;


import com.HW6.demo.Entities.LoginResponse;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam  String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        LoginResponse loginResponse = authService.login(username,password);
        // we return the loginResponse object
        //we also set the Cookie which will have the refreshToken, as

        Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponse);
    }

    // We are required to pass the whole body: User object.
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        authService.signup(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(" User Successfully created.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){

    }


}
