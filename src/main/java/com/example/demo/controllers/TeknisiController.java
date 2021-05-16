package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.TeknisiModel;
import com.example.demo.services.TeknisiService;
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
@RequestMapping(path = "api/v1/teknisi")
public class TeknisiController {
    private final TeknisiService teknisiService;
    private Message message;

    @Autowired
    public TeknisiController(TeknisiService teknisiService, Message message) {
        this.teknisiService = teknisiService;
        this.message = message;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<TeknisiModel> allTeknisi = teknisiService.findAllTeknisi();
        return new ResponseEntity<>(allTeknisi, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> findOneTeknisi(@PathVariable(value = "id") Integer id) {
        TeknisiModel teknisiModel = teknisiService.findOneTeknisi(id);
        if (teknisiModel != null) {
            return new ResponseEntity<>(teknisiModel, HttpStatus.OK);
        } else {
            message.setMessage("Teknisi not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody TeknisiModel teknisiModel) {
        teknisiService.createTeknisi(teknisiModel);
        message.setMessage("Data berhasil dibuat");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateTeknisi(@RequestBody TeknisiModel teknisiModel) {
        String result = teknisiService.updateTeknisi(teknisiModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTeknisi(@PathVariable(value = "id") Integer id) {
        String teknisiModel = teknisiService.deleteTeknisi(id);
        return new ResponseEntity<>(teknisiModel, HttpStatus.OK);
    }
}
