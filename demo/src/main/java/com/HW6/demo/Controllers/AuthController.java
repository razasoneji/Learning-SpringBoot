package com.HW6.demo.Controllers;


import com.HW6.demo.Entities.LoginResponse;
import com.HW6.demo.Entities.User;
import com.HW6.demo.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam  String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        log.info(" Received login request in AuthController username:{},password:{}", username, password);
        LoginResponse loginResponse = authService.login(username,password);
        log.info(" Login Response:{}", loginResponse);
        // we return the loginResponse object
        //we also set the Cookie which will have the refreshToken, as

        Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        log.info("Created Cookie and returning it along with response.");
        return ResponseEntity.ok(loginResponse);
    }

    // We are required to pass the whole body: User object.
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user){
        log.info(" Received user signup request in AuthController username:{}", user.getUsername());
        authService.signup(user);
        log.info("Sending signup response string with status code HttpStatus.CREATED ");
        return ResponseEntity.status(HttpStatus.CREATED).body(" User Successfully created.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh( @CookieValue(name = "refreshToken", required = false) String refreshToken,HttpServletRequest request, HttpServletResponse response){
       log.info(" Received refresh request in AuthController refreshToken:{}", refreshToken);
       if(refreshToken == null){
           log.info(" Refresh token is null in AuthController refreshToken:{}", refreshToken);
           throw new RuntimeException("Refresh token is null in AuthController refreshToken");
       }
       log.info(" Refresh token:{}", refreshToken);
       log.info("Going to call refresh method from controller");
       LoginResponse loginResponse = authService.refresh(refreshToken);
       log.info("Received refresh loginResponse in authcontroller");
       return ResponseEntity.ok(loginResponse);


    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response,
                                         @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        log.info("Call received to logout in AuthController refreshToken:{}", refreshToken);
        if (refreshToken != null) {
            log.info("refresh token is not null");
            authService.deleteByRefreshToken(refreshToken);
        }
        else{
            log.info("Refresh token is null");
            return ResponseEntity.status(HttpStatus.OK).body("Refresh token is null,already logged out");
        }

        // Clear the refresh token cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use true in production
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }

}
