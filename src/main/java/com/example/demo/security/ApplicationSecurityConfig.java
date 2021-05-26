package com.example.demo.security;

import com.example.demo.configurations.jwt.AuthenticationFilter;
import com.example.demo.configurations.jwt.AuthorizationFilter;
import com.example.demo.configurations.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetail userDetail;

    @Autowired
    private final JwtProvider jwtProvider;

    public ApplicationSecurityConfig(UserDetail userDetail, JwtProvider jwtProvider) {
        this.userDetail = userDetail;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/v1/user/register").permitAll().anyRequest()
                .authenticated().and().addFilter(new AuthenticationFilter(authenticationManager(), jwtProvider))
                .addFilterAfter(new AuthorizationFilter(jwtProvider), AuthenticationFilter.class).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
    }
}
