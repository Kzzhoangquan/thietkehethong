package com.example.databaseservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ketquanhandang")
@Data
public class KetQuaNhanDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngaynhandang")
    @Temporal(TemporalType.DATE)
    private Date ngaynhandang;

    @ManyToOne
    @JoinColumn(name = "mohinhid")
    private MoHinh mohinh;

    @OneToMany(mappedBy = "ketQuaNhanDang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HanhViMau> hanhViMaus = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgaynhandang() {
        return ngaynhandang;
    }

    public void setNgaynhandang(Date ngaynhandang) {
        this.ngaynhandang = ngaynhandang;
    }

    public MoHinh getMohinh() {
        return mohinh;
    }

    public void setMohinh(MoHinh mohinh) {
        this.mohinh = mohinh;
    }

}
