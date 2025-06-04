package com.example.databaseservice.Model;

public class TKHanhVi extends HanhVi {
    private Long soluong;

    public TKHanhVi() {
        super();
    }

    public TKHanhVi(Long id, String ten, String mucdo, String mota, Long soluong) {
        this.setId(id);
        this.setTen(ten);
        this.setMucdo(mucdo);
        this.setMota(mota);
        this.soluong = soluong;
    }

    public Long getSoluong() {
        return soluong;
    }

    public void setSoluong(Long soluong) {
        this.soluong = soluong;
    }
}

