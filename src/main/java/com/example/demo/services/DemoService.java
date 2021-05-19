package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DemoService {
    private final Path root = Paths.get("uploads");

    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
                System.out.println("SUCCESS");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(MultipartFile file) {
        try {
            System.out.println(file.getInputStream());
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
