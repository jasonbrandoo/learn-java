package com.example.demo.configurations.jwt;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private String prefix = "Bearer ";
    private long expired = 5;

    public String generated(Authentication authentication) {
        System.out.println(authentication.getPrincipal());
        return Jwts.builder().setSubject(authentication.getName()).claim("authorities", authentication.getAuthorities())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expired).toInstant())).signWith(secretKey)
                .compact();
    }

    public Jws<Claims> parser(String token) {
        System.out.println("TOKEEEEN " + token);
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }
}
