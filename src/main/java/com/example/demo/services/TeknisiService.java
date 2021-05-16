package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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

    public TeknisiModel findOneTeknisi(Integer id) {
        String sql = "SELECT id, nama FROM teknisi WHERE id = ?";
        List<TeknisiModel> teknisiModel = jdbcTemplate.query(sql, new TeknisiMapper(), id);
        if (teknisiModel.isEmpty()) {
            return null;
        } else {
            return teknisiModel.get(0);
        }
    }

    public List<TeknisiModel> findAllTeknisi() {
        String sql = "SELECT * FROM teknisi";
        return jdbcTemplate.query(sql, new TeknisiMapper());
    }

    public String updateTeknisi(TeknisiModel teknisiModel) {
        String sql = "UPDATE teknisi SET nama = ? WHERE id = ?";
        Optional<Integer> data = Optional
                .of(jdbcTemplate.update(sql, new Object[] { teknisiModel.getNama(), teknisiModel.getId() }));
        if (data.get() == 1) {
            return "Data berhasil diupdate";
        } else {
            return "Data tidak temukan";
        }
    }

    public String deleteTeknisi(Integer id) {
        String sql = "DELETE FROM teknisi WHERE id = ?";
        Optional<Integer> teknisiModel = Optional.of(jdbcTemplate.update(sql, id));
        if (teknisiModel.get() == 1) {
            return "Data berhasil dihapus";
        } else {
            return "Data tidak temukan";
        }
    }
}
