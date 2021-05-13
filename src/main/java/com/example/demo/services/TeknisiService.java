package com.example.demo.services;

import java.util.List;

import com.example.demo.models.TeknisiModel;
import com.example.demo.utils.TeknisiMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeknisiService {

    private final JdbcTemplate jdbcTemplate;

    public TeknisiService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTeknisi(TeknisiModel teknisiModel) {
        String sql = "INSERT INTO teknisi(nama) VALUES (?)";
        jdbcTemplate.update(sql, teknisiModel.getNama());
    }

    public TeknisiModel findOneTeknisi(Integer id){
        String sql = "SELECT id, nama FROM teknisi WHERE id = ?";
        TeknisiModel teknisiModel = jdbcTemplate.queryForObject(sql, new TeknisiMapper(), id);
        return teknisiModel;
    }

    public List<TeknisiModel> findAllTeknisi() {
        String sql = "SELECT * FROM teknisi";
        return jdbcTemplate.query(sql, new TeknisiMapper());
    }

    public void updateTeknisi(TeknisiModel teknisiModel) {
        String sql = "UPDATE teknisi SET nama = ? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{teknisiModel.getNama(), teknisiModel.getId()});
    }

    public void deleteTeknisi (Integer id){
        String sql = "DELETE FROM teknisi WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
