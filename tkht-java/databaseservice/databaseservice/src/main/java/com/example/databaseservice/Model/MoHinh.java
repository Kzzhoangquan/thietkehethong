package com.example.databaseservice.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mohinh")
@Data
public class MoHinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "phienban")
    private String phienban;

    @Column(name = "dochinhxac")
    private Float dochinhxac;

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

    public MoHinh() {
    }
}
