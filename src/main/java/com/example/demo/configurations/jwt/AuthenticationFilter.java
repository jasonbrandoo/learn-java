package com.example.demo.configurations.jwt;

import java.io.IOException;

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

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        setFilterProcessesUrl("/api/v1/login");
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
        String token = jwtProvider.generated(authResult.getName());
        response.addHeader("Authorization", token);
    }
}
