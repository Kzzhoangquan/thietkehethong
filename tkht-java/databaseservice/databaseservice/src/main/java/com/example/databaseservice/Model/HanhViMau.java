package com.example.databaseservice.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hanhvimau")
@Data
public class HanhViMau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mauid", nullable = false)
    private Mau mau;

    @ManyToOne
    @JoinColumn(name = "hanhviid", nullable = false)
    private HanhVi hanhVi;

    @ManyToOne
    @JoinColumn(name = "ketquanhandangid")
    private KetQuaNhanDang ketQuaNhanDang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KetQuaNhanDang getKetQuaNhanDang() {
        return ketQuaNhanDang;
    }

    public void setKetQuaNhanDang(KetQuaNhanDang ketQuaNhanDang) {
        this.ketQuaNhanDang = ketQuaNhanDang;
    }

    public HanhVi getHanhVi() {
        return hanhVi;
    }

    public void setHanhVi(HanhVi hanhVi) {
        this.hanhVi = hanhVi;
    }

    public Mau getMau() {
        return mau;
    }

    public void setMau(Mau mau) {
        this.mau = mau;
    }
}