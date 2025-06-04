package com.example.thongkeservice.Model;

public class TKMoHinh extends MoHinh {
    private Long soluong;

    public TKMoHinh() {
        super();
    }

    public TKMoHinh(Long id, String ten, String phienban, Float dochinhxac, Long soluong) {
        super(id, ten, phienban, dochinhxac);
        this.soluong = soluong;
    }

    public Long getSoluong() {
        return soluong;
    }
    public void setSoluong(Long soluong) {
        this.soluong = soluong;
    }
}
