package com.example.demo.configurations.jwt;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public AuthorizationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (Objects.isNull(header) || !header.startsWith("Bearer")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Authorization", "");

        if (header.equals("Authorization") || header.startsWith("Bearer")) {

            try {
                String username = jwtProvider.parser(token);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                throw new AccessDeniedException("User authorization not resolved");
            }
        } else {
            throw new AccessDeniedException("Authorization token not found");
        }
    }
}
