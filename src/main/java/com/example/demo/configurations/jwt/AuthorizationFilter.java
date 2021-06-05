package com.example.demo.configurations.jwt;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

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

        if (header.equals("Authorization") || header.startsWith("Bearer")) {
            String token = header.replace("Bearer ", "");
            try {
                Jws<Claims> jwt = jwtProvider.parser(token);
                String username = jwt.getBody().getSubject();
                Claims body = jwt.getBody();
                List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
                List<GrantedAuthority> authority = new ArrayList<>();
                for (Map<String, String> map : authorities) {
                    authority.add(new SimpleGrantedAuthority(map.get("authority")));
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        null, authority);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                throw new AccessDeniedException(e.getMessage());
            }
        } else {
            throw new AccessDeniedException("Authorization token not found");
        }
    }
}
