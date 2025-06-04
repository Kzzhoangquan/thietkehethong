package com.example.thongkeservice.Controller;

import com.example.thongkeservice.Model.*;
import com.example.thongkeservice.Service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/thongke")
    public String hienThiTrangThongKe() {
        return "thongke";
    }

    @GetMapping("/thongkemauhanhvi")
    @ResponseBody
    public List<TKHanhVi> thongKeTheoHanhVi(@RequestParam String ngayBatDau, @RequestParam String ngayKetThuc) {
        List<TKHanhVi> list = thongKeService.thongKeMauTheoHanhVi(ngayBatDau, ngayKetThuc);
        return list;
    }

    @GetMapping("/thongkemaumohinh")
    @ResponseBody
    public List<TKMoHinh> thongKeTheoMoHinh(@RequestParam String ngayBatDau, @RequestParam String ngayKetThuc) {
        List<TKMoHinh> list = thongKeService.thongKeMauTheoMoHinh(ngayBatDau, ngayKetThuc);
        return list;
    }

    @GetMapping("/dsmauhanhvi")
    @ResponseBody
    public List<Mau> layDanhSachMauTheoHanhVi(
            @RequestParam Long id,
            @RequestParam String ngayBatDau,
            @RequestParam String ngayKetThuc) {

        HanhVi hv = new HanhVi(id);
        List<Mau> list = thongKeService.layDsMauTheoHanhVi(hv, ngayBatDau, ngayKetThuc);
        return list;
    }

    @GetMapping("/dsmaumohinh")
    @ResponseBody
    public List<Mau> layDanhSachMauTheoMoHinh(
            @RequestParam Long id,
            @RequestParam String ngayBatDau,
            @RequestParam String ngayKetThuc) {

        MoHinh mh = new MoHinh(id);
        List<Mau> list = thongKeService.layDsMauTheoMoHinh(mh, ngayBatDau, ngayKetThuc);
        return list;
    }


}

