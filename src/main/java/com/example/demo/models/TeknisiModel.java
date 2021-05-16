package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;

public class TeknisiModel {
    private Integer id;

    @ApiModelProperty(notes = "Nama teknisi", name = "nama", required = true, value = "Helia")
    private String nama;

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
