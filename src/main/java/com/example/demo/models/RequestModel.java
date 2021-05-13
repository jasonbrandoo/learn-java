package com.example.demo.models;

public class RequestModel {
    private Integer id;
    private String nama;
    private Integer teknisi_id;
    
    public RequestModel(Integer id, String nama, Integer teknisi_id) {
        this.id = id;
        this.nama = nama;
        this.teknisi_id = teknisi_id;
    }

    public RequestModel(String nama, Integer teknisi_id) {
        this.nama = nama;
        this.teknisi_id = teknisi_id;
    }

    public RequestModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getTeknisi_id() {
        return teknisi_id;
    }

    public void setTeknisi_id(Integer teknisi_id) {
        this.teknisi_id = teknisi_id;
    }

    @Override
    public String toString() {
        return "RequestModel [id=" + id + ", nama=" + nama + ", teknisi_id=" + teknisi_id + "]";
    }

}
