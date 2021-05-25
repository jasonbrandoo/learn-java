package com.example.demo.controllers;

import com.example.demo.models.UsersModel;
import com.example.demo.services.UserService;
import com.example.demo.utils.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Message message;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody UsersModel usersModel) {
        int insert = userService.insert(usersModel);
        if (insert == 0) {
            message.setMessage("Registration failed");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message.setMessage("Registration success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
