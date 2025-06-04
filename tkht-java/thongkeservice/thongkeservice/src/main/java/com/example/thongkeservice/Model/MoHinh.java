package com.example.thongkeservice.Model;

public class MoHinh {
    private Long id;
    private String ten;
    private String phienban;
    private Float dochinhxac;

    public MoHinh() {
    }

    public MoHinh(Long id, String ten, String phienban, Float dochinhxac) {
        this.id = id;
        this.ten = ten;
        this.phienban = phienban;
        this.dochinhxac = dochinhxac;
    }

    public MoHinh(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhienban() {
        return phienban;
    }

    public void setPhienban(String phienban) {
        this.phienban = phienban;
    }

    public Float getDochinhxac() {
        return dochinhxac;
    }

    public void setDochinhxac(Float dochinhxac) {
        this.dochinhxac = dochinhxac;
    }
}
