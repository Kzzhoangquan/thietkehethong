package com.example.thongkeservice.Model;

public class HanhVi {
    private Long id;
    private String ten;
    private String mucdo;
    private String mota;

    public HanhVi() {}

    public HanhVi(Long id, String ten, String mucdo, String mota) {
        this.id = id;
        this.ten = ten;
        this.mucdo = mucdo;
        this.mota = mota;
    }

    public HanhVi(Long id) {
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

    public String getMucdo() {
        return mucdo;
    }

    public void setMucdo(String mucdo) {
        this.mucdo = mucdo;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
