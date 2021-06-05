package com.example.demo.services;

import java.util.List;

import com.example.demo.models.UserModel;
import com.example.demo.utils.UserMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserService(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public int insert(UserModel userModel) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
        String username = userModel.getUsername();
        String password = userModel.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        int isCreate = jdbcTemplate.update(sql, new Object[] { username, encodedPassword });
        return isCreate;
    }

    public UserModel findOne(String username) {
        String sql = "SELECT username, password FROM users WHERE username = ?";
        List<UserModel> user = jdbcTemplate.query(sql, new UserMapper(), username);
        return user.get(0);
    }
}
