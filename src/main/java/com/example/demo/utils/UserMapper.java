package com.example.demo.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.models.UserModel;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserModel> {
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel usersModel = new UserModel();
        // usersModel.setId(rs.getLong("id"));
        usersModel.setUsername(rs.getString("username"));
        usersModel.setPassword(rs.getString("password"));
        return usersModel;
    }
}
