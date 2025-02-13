package com.project.basicsessionspringsecurity.Services;

import com.project.basicsessionspringsecurity.Entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;


@Service
public class JwtService {


    private final String secretKey;

    public JwtService(@Value("${jwt.secret.key}") String secretKey) { // ✅ Constructor injection
        this.secretKey = secretKey;
    }

    public String generateJwt(User user) {
        return Jwts.builder() // build using builder CDP
                .subject(user.getId().toString()) // subject is typically the username or id
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(1*60*60)))
                .claim("email", user.getEmail())
                .signWith(getSigningKey())
                .compact();//Converts it into a compact jwt String
    }

    public Long getIdFromJwt(String jwt) {
        return Long.valueOf( extractClaims(jwt).getSubject() );


    }

    public String getEmailFromJwt(String jwt) {
        return extractClaims(jwt)
                .get("email", String.class);

    }

    public boolean isTokenExpired(String jwt) {
        return (extractExpiration(jwt).before(Date.from(Instant.now()) ));


    }

    public Date extractExpiration(String jwt) {
        return extractClaims(jwt).getExpiration();
    }

    public boolean validateJwt(String jwt, String email) {
        return email.equals(getEmailFromJwt(jwt)) && !isTokenExpired(jwt);

    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Claims extractClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build() //built a parser till now
                .parseSignedClaims(jwt)
                .getPayload();
    }


}
