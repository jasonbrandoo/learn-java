package com.example.demo.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.models.RequestModel;

import org.springframework.jdbc.core.RowMapper;

public class RequestMapper implements RowMapper<RequestModel> {
    public RequestModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        RequestModel requestModel = new RequestModel();
        requestModel.setId(rs.getInt("id"));
        requestModel.setNama(rs.getString("nama"));
        requestModel.setTeknisi_id(rs.getInt("teknisi_id"));
        return requestModel;
    }
}
