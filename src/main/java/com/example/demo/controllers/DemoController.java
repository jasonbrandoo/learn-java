package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.RequestModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class DemoController {
    
    @GetMapping
    public List<RequestModel> getAllRequest() {
        RequestModel requestModel = new RequestModel(1, "Fix", 2);
        System.out.println(requestModel.toString());
        return List.of(new RequestModel(2, "First Request", 2));
    }
}
