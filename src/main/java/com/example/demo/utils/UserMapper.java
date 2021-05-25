package com.example.demo.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.models.UsersModel;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UsersModel> {
    public UsersModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsersModel usersModel = new UsersModel();
        usersModel.setId(rs.getLong("id"));
        usersModel.setUsername(rs.getString("username"));
        usersModel.setPassword(rs.getString("password"));
        return usersModel;
    }
}
