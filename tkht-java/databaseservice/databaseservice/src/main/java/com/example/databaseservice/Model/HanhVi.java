package com.example.databaseservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hanhvi")
@Data
public class HanhVi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "mucdo")
    private String mucdo;

    @Column(name = "mota")
    private String mota;

    @OneToMany(mappedBy = "hanhVi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HanhViMau> hanhViMaus = new ArrayList<>();

    public HanhVi(String ten) {
        this.ten = ten;
    }

    public HanhVi() {
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
