package com.example.demo.security;

import java.util.List;

import com.example.demo.models.UsersModel;
import com.example.demo.utils.UserMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetail implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UserDetail(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<UsersModel> userModel = jdbcTemplate.query(sql, new UserMapper(), username);
        if (userModel.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        UsersModel data = userModel.get(0);
        System.out.println("++++++++++++++++++++++++++++++++++++" + data.toString());
        return User.withUsername(data.getUsername()).password(data.getPassword()).authorities(List.of())
                .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
    }

}
