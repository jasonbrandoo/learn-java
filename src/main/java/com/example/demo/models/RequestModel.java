package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;

public class RequestModel {
    private Integer id;

    @ApiModelProperty(notes = "Nama request", name = "nama", required = true, value = "Bug fix")
    private String nama;

    @ApiModelProperty(notes = "Nama request", name = "teknisi_id", required = true, value = "1")
    private Integer teknisi_id;

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
    // ttttt

}
