package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.services.DemoService;
import com.example.demo.utils.ImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "api/v1")
public class DemoController {
    @Autowired
    DemoService demoService;
    ImageUtil imageUtil;

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestBody MultipartFile file) {
        String message = "";
        System.out.println(file.getOriginalFilename());
        try {
            demoService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            String filename = file.getOriginalFilename();
            Optional<String> fileType = Optional.ofNullable(filename).filter(f -> f.contains("."))
                    .map(f -> f.substring(filename.lastIndexOf(".") + 1));
            System.out.println(fileType);
            String base = ImageUtil.convertImagesBase64(file.getBytes(), fileType.get());
            System.out.println(base.toString());
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            System.out.println(e);
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
