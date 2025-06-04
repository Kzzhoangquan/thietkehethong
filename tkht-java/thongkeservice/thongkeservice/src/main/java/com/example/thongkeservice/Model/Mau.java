package com.example.thongkeservice.Model;

import java.util.Date;

public class Mau {
    private Long id;
    private String loaifile;
    private String videopath;
    private Date ngaytao;

    public Mau() {
    }

    public Mau(Long id, String loaifile, String videopath, Date ngaytao) {
        this.id = id;
        this.loaifile = loaifile;
        this.videopath = videopath;
        this.ngaytao = ngaytao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoaifile() {
        return loaifile;
    }

    public void setLoaifile(String loaifile) {
        this.loaifile = loaifile;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }
}
