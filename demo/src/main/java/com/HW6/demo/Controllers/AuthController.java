package com.HW6.demo.Controllers;


import com.HW6.demo.Entities.LoginResponse;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(loginResponse);
    }

    // We are required to pass the whole body: User object.
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        authService.signup(user);
        return ResponseEntity.ok("User successfully created");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){

    }


}
