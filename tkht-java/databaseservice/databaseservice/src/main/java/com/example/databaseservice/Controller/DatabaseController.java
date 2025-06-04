package com.example.databaseservice.Controller;


import com.example.databaseservice.Model.*;
import com.example.databaseservice.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequestMapping("/database")
@RestController
public class DatabaseController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HanhViService hanhViService;

    @Autowired
    private MauService mauService;

    @Autowired
    private MoHinhService moHinhService;

    @Autowired
    private KetQuaNhanDangService ketQuaNhanDangService;

    @Autowired
    private MoHinhMauService moHinhMauService;

    @Autowired
    private HanhViMauService hanhViMauService;

    @GetMapping("/models")
    public ResponseEntity<List<MoHinh>>getAllMohinh() {
        List<MoHinh> mohinhList = moHinhService.getAllMohinh();
        return ResponseEntity.ok(mohinhList);
    }

    @Transactional
    @PostMapping("/save-result")
    public String saveResult(@RequestBody Map<String, Object> payload) {
        @SuppressWarnings("unchecked")
        Map<String, Object> moHinhData = (Map<String, Object>) payload.get("moHinh");
        MoHinh moHinh = objectMapper.convertValue(moHinhData, MoHinh.class);
        @SuppressWarnings("unchecked")
        Map<String, Object> mauData = (Map<String, Object>) payload.get("mau");
        Mau mau = objectMapper.convertValue(mauData, Mau.class);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> hanhViList = (List<Map<String, Object>>) payload.get("hanhVis");
        List<HanhVi> hanhVis = hanhViList.stream()
                .map(data -> objectMapper.convertValue(data, HanhVi.class))
                .collect(Collectors.toList());
        if (hanhVis.isEmpty()) {
            return "No actions to process. Data not saved.";
        }
        try {
            KetQuaNhanDang kq = new KetQuaNhanDang();
            kq.setMohinh(moHinh);
            kq.setNgaynhandang(new Date());
            KetQuaNhanDang ketQuaNhanDang = ketQuaNhanDangService.saveKetQuaNhanDang(kq);
            Mau mauSave = mauService.saveMau(mau);
            for (HanhVi i  : hanhVis) {
                HanhVi hanhVi = hanhViService.findOrCreateHanhVi(i);
                HanhViMau hanhViMau = new HanhViMau();
                hanhViMau.setMau(mauSave);
                hanhViMau.setHanhVi(hanhVi);
                hanhViMau.setKetQuaNhanDang(ketQuaNhanDang);
                HanhViMau hanhViMau1 = hanhViMauService.saveHanhViMau(hanhViMau);
            }
            return "Data saved successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error: Failed to save data. " + e.getMessage());  // Ném lỗi để Spring có thể rollback
        }
    }

    @GetMapping("/thongkemauhanhvi")
    public ResponseEntity<?> thongKeMauTheoHanhVi(
            @RequestParam("ngayBatDau") String ngayBatDauStr,
            @RequestParam("ngayKetThuc") String ngayKetThucStr) {
        List<TKHanhVi> thongKe = hanhViMauService.thongKeMauTheoHanhVi(ngayBatDauStr, ngayKetThucStr);
        return ResponseEntity.ok(thongKe);
    }

    @GetMapping("/thongkemaumohinh")
    public ResponseEntity<?> thongKeMauTheoMoHinh(
            @RequestParam("ngayBatDau") String ngayBatDauStr,
            @RequestParam("ngayKetThuc") String ngayKetThucStr) {

        List<TKMoHinh> thongKe = moHinhMauService.thongKeMauTheoMoHinh(ngayBatDauStr, ngayKetThucStr);
        return ResponseEntity.ok(thongKe);
    }

    @PostMapping("/dsmauhanhvi")
    public ResponseEntity<?> danhSachMauTheoHanhVi(@RequestBody Map<String, Object> request) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> hanhViMap = (Map<String, Object>) request.get("hanhvi");
        HanhVi hv = mapper.convertValue(hanhViMap, HanhVi.class);
        String ngayBatDau = (String) request.get("ngayBatDau");
        String ngayKetThuc = (String) request.get("ngayKetThuc");
        List<Mau> list = hanhViMauService.layDanhSachMauTheoHanhVi(hv, ngayBatDau, ngayKetThuc);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/dsmaumohinh")
    public ResponseEntity<?> danhSachMauTheoMoHinh(@RequestBody Map<String, Object> request) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> moHinhMap = (Map<String, Object>) request.get("mohinh");
        MoHinh moHinh = mapper.convertValue(moHinhMap, MoHinh.class);
        String ngayBatDau = (String) request.get("ngayBatDau");
        String ngayKetThuc = (String) request.get("ngayKetThuc");
        List<Mau> list = moHinhMauService.layDanhSachMauTheoMoHinh(moHinh, ngayBatDau, ngayKetThuc);
        return ResponseEntity.ok(list);
    }

}
