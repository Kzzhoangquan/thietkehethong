package com.example.cameraservice.Model;

import lombok.Data;

@Data
public class MoHinh {
    private Long id;
    private String ten;
    private String phienban;
    private Float dochinhxac; // Dùng Float để khớp với dữ liệu từ Database Service

    public String getPhienban() {
        return phienban;
    }

    public void setPhienban(String phienban) {
        this.phienban = phienban;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDochinhxac() {
        return dochinhxac;
    }

    public void setDochinhxac(Float dochinhxac) {
        this.dochinhxac = dochinhxac;
    }
}