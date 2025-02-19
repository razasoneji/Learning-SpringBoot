package com.HW6.demo.Configs;


import com.HW6.demo.Entities.Subscription;
import com.HW6.demo.Filters.JwtAuthFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.HW6.demo.Entities.Subscription.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String[] publicRoutes ={
            "/auth/login","/auth/signup","/auth/refresh","/auth/logout"
    }; //logout not to be included here, as only authenticated users can logout.


    public static final String[] freeRoutes ={
            "/plan/free"
    };

    public static final String[] basicRoutes ={
            "/plan/basic"
    };

    public static final String[] premiumRoutes ={
            "/plan/premium"
    };


    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll() // Allow public access to auth endpoints
                        .requestMatchers(freeRoutes).hasAuthority(FREE.name())// Only users with Free plan can access
                        .requestMatchers(basicRoutes).hasAuthority(BASIC.name())// Only users with Basic plan can access
                        .requestMatchers(premiumRoutes).hasAuthority(PREMIUM.name()) // Only users with PREMIUM plan can access
                        .anyRequest().authenticated() // Secure all other endpoints , including logout too
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
