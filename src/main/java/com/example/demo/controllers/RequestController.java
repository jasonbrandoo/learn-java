package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.RequestModel;
import com.example.demo.services.RequestService;
import com.example.demo.utils.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/request")
public class RequestController {
    private final RequestService requestService;
    private Message message;

    @Autowired
    public RequestController(RequestService requestService, Message message) {
        this.requestService = requestService;
        this.message = message;
    }

    @GetMapping
    public ResponseEntity<Object> findAllRequest() {
        List<RequestModel> allRequest = requestService.findAllRequest();
        return new ResponseEntity<>(allRequest, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> findOneRequest(@PathVariable(value = "id") Integer id){
        try {
            RequestModel requestModel = requestService.findOneRequest(id);
            return new ResponseEntity<>(requestModel, HttpStatus.OK);
        } catch (Exception e) {
            message.setMessage("Request not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createRequest(@RequestBody RequestModel requestModel){
        int insert = requestService.createRequest(requestModel);
        if (insert == 0){
            message.setMessage("Sorry technician with that id not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateTeknisi(@RequestBody RequestModel requestModel){
        requestService.updateRequest(requestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTeknisi(@PathVariable(value = "id") Integer id){
        requestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
