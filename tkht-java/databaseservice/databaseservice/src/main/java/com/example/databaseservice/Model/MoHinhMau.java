package com.example.databaseservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "mohinhmau")
@Data
public class MoHinhMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngayhuanluyen")
    @Temporal(TemporalType.DATE)
    private Date ngayhuanluyen;

    @ManyToOne
    @JoinColumn(name = "mohinhid")
    private MoHinh mohinh;

    @ManyToOne
    @JoinColumn(name = "mauid")
    private Mau mau;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayhuanluyen() {
        return ngayhuanluyen;
    }

    public void setNgayhuanluyen(Date ngayhuanluyen) {
        this.ngayhuanluyen = ngayhuanluyen;
    }

    public MoHinh getMohinh() {
        return mohinh;
    }

    public void setMohinh(MoHinh mohinh) {
        this.mohinh = mohinh;
    }

    public Mau getMau() {
        return mau;
    }

    public void setMau(Mau mau) {
        this.mau = mau;
    }
}
