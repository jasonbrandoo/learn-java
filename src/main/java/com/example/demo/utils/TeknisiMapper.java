package com.example.demo.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.models.TeknisiModel;

import org.springframework.jdbc.core.RowMapper;

public class TeknisiMapper implements RowMapper<TeknisiModel> {
    public TeknisiModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeknisiModel teknisiModel = new TeknisiModel();
        teknisiModel.setId(rs.getInt("id"));
        teknisiModel.setNama(rs.getString("nama"));
        return teknisiModel;
    }
}
