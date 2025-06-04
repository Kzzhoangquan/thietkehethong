package com.example.databaseservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "mau")
@Data
public class Mau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loaifile")
    private String loaifile;

    @Column(name = "videopath",nullable = false)
    private String videopath;

    @Column(name = "ngaytao")
    @Temporal(TemporalType.DATE)
    private Date ngaytao;

    @OneToMany(mappedBy = "mau", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HanhViMau> hanhViMaus = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Mau() {
    }
    public Mau(String videopath) {
        this.videopath = videopath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getLoaifile() {
        return loaifile;
    }

    public void setLoaifile(String loaifile) {
        this.loaifile = loaifile;
    }

}