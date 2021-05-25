package com.example.demo.configurations.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.models.UsersModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class UsernameAndPasswordFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    public UsernameAndPasswordFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UsersModel readValue = new ObjectMapper().readValue(request.getInputStream(), UsersModel.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(readValue.getUsername(),
                    readValue.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            System.out.println(authenticate);
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        String key = "securesecuresecuresecuresecuresecuresecuresecure";
        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).signWith(Keys.hmacShaKeyFor(key.getBytes())).compact();
        response.addHeader("Authorization", token);
    }

}
