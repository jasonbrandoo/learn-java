package com.example.demo.services;

import com.example.demo.models.UsersModel;
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

    public int insert(UsersModel usersModel) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
        String username = usersModel.getUsername();
        String password = usersModel.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        int isCreate = jdbcTemplate.update(sql, new Object[] { username, encodedPassword });
        return isCreate;
    }
}
