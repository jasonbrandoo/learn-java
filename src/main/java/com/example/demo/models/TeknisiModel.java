package com.example.demo.models;

public class TeknisiModel {
    private Integer id;
    private String nama;
    
    public TeknisiModel(Integer id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public TeknisiModel(String nama) {
        this.nama = nama;
    }

    public TeknisiModel() {
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

    @Override
    public String toString() {
        return "TeknisiModel [nama=" + nama + ", id=" + id + "]";
    }
}
