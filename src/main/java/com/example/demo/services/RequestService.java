package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.RequestModel;
import com.example.demo.models.TeknisiModel;
import com.example.demo.utils.RequestMapper;
import com.example.demo.utils.TeknisiMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private final JdbcTemplate jdbcTemplate;

    public RequestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createRequest(RequestModel requestModel) {
        String findSql = "SELECT id, nama FROM teknisi WHERE id = ?";
        List<TeknisiModel> getTeknisi = jdbcTemplate.query(findSql, new TeknisiMapper(), requestModel.getTeknisi_id());
        int isFound = getTeknisi.size();
        if (isFound == 1) {
            String sql = "INSERT INTO request(nama, teknisi_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, new Object[] { requestModel.getNama(), requestModel.getTeknisi_id() });
        }
        return isFound;
    }

    public RequestModel findOneRequest(Integer id) {
        String sql = "SELECT id, nama, teknisi_id FROM request WHERE id = ?";
        List<RequestModel> requestModel = jdbcTemplate.query(sql, new RequestMapper(), id);
        if (requestModel.isEmpty()) {
            return null;
        } else {
            return requestModel.get(0);
        }
    }

    public List<RequestModel> findAllRequest() {
        String sql = "SELECT * FROM request";
        return jdbcTemplate.query(sql, new RequestMapper());
    }

    public String updateRequest(RequestModel requestModel) {
        String sql = "UPDATE request SET nama = ? WHERE id = ? AND teknisi_id = ?";
        Optional<Integer> data = Optional.of(jdbcTemplate.update(sql,
                new Object[] { requestModel.getNama(), requestModel.getId(), requestModel.getTeknisi_id() }));
        if (data.get() == 1) {
            return "Data berhasil diupdate";
        } else {
            return "Data tidak temukan";
        }
    }

    public String deleteRequest(Integer id) {
        String sql = "DELETE FROM request WHERE id = ?";
        Optional<Integer> data = Optional.of(jdbcTemplate.update(sql, id));
        if (data.get() == 1) {
            return "Data berhasil dihapus";
        } else {
            return "Data tidak temukan";
        }
    }
}
