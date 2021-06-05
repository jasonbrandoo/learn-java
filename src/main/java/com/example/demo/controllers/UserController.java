package com.example.demo.controllers;

import com.example.demo.configurations.jwt.JwtProvider;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import com.example.demo.utils.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    // private UserService userService;
    private Message message;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
    // @PostMapping(path = "/register")
    // public ResponseEntity<Object> register(@RequestBody UserModel usersModel) {
    // int insert = userService.insert(usersModel);
    // if (insert == 0) {
    // message.setMessage("Registration failed");
    // return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    // }
    // message.setMessage("Registration success");
    // return new ResponseEntity<>(message, HttpStatus.OK);
    // }

    @GetMapping(path = "/detail")
    @PreAuthorize("hasRole('ADMINS')")
    public ResponseEntity<Object> detail() {
        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody UserModel userModel) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userModel.getUsername(),
                userModel.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            var token = jwtProvider.generated(SecurityContextHolder.getContext().getAuthentication());
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("FAILLLLL");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
