package com.example.demo.configurations.jwt;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private String prefix = "Bearer ";
    private long expired = 5;

    public String generated(String subject) {
        return Jwts.builder().setSubject(subject)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expired).toInstant())).signWith(secretKey)
                .compact();
    }

    public String parser(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token.replace(prefix, "")).getBody()
                .getSubject();
    }
}
