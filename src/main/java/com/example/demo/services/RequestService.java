package com.example.demo.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.RequestModel;
import com.example.demo.models.TeknisiModel;
import com.example.demo.utils.RequestMapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private final JdbcTemplate jdbcTemplate;

    public RequestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createRequest(RequestModel requestModel) {
        String findSql = "SELECT id, nama FROM teknisi WHERE id = ?";
        List<TeknisiModel> getTeknisi = jdbcTemplate.query(findSql, new ResultSetExtractor<List<TeknisiModel>>(){
            public List<TeknisiModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<TeknisiModel> teknisiModels = new ArrayList<>();
                while(rs.next()){
                    TeknisiModel teknisiModel = new TeknisiModel();
                    teknisiModel.setId(rs.getInt("id"));
                    teknisiModel.setNama(rs.getString("nama"));
                    teknisiModels.add(teknisiModel);
                }
                return teknisiModels;
            }
        }, requestModel.getTeknisi_id());
        int isFound = getTeknisi.size();
        if (isFound == 1){
            String sql = "INSERT INTO request(nama, teknisi_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, new Object[]{requestModel.getNama(), requestModel.getTeknisi_id()});
        }
        return isFound;
    }

    public RequestModel findOneRequest(Integer id){
        String sql = "SELECT id, nama, teknisi_id FROM request WHERE id = ?";
        RequestModel requestModel = jdbcTemplate.queryForObject(sql, new RequestMapper(), id);
        return requestModel;
    }

    public List<RequestModel> findAllRequest() {
        String sql = "SELECT * FROM request";
        return jdbcTemplate.query(sql, new RequestMapper());
    }

    public void updateRequest(RequestModel requestModel) {
        String sql = "UPDATE request SET nama = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{requestModel.getNama(), requestModel.getId()});
    }

    public void deleteRequest (Integer id){
        String sql = "DELETE FROM request WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
