package com.example.databaseservice.Service;

import com.example.databaseservice.Model.*;
import com.example.databaseservice.Repository.HanhViMauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HanhViMauService {

    @Autowired
    private HanhViMauRepository hanhViMauRepository;

    public HanhViMau saveHanhViMau(HanhViMau hvm) {
//        HanhViMau hanhViMau = new HanhViMau();
//        hanhViMau.setMau(mau);
//        hanhViMau.setHanhVi(hanhVi);
//        hanhViMau.setKetQuaNhanDang(ketQuaNhanDang);
        return hanhViMauRepository.save(hvm);
    }
    public List<TKHanhVi> thongKeMauTheoHanhVi(String ngayBatDauStr, String ngayKetThucStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayBatDau = dateFormat.parse(ngayBatDauStr);
            Date ngayKetThuc = dateFormat.parse(ngayKetThucStr);

            List<Object[]> results = hanhViMauRepository.countMauByHanhVi(ngayBatDau, ngayKetThuc);

            return results.stream()
                    .map(row -> new TKHanhVi(
                            (Long) row[0],
                            (String) row[1],
                            (String) row[2],
                            (String) row[3],
                            (Long) row[4]
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi chuyển đổi ngày: " + e.getMessage());
        }
    }

    public List<Mau> layDanhSachMauTheoHanhVi(HanhVi hanhVi,String ngayBatDauStr, String ngayKetThucStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayBatDau = dateFormat.parse(ngayBatDauStr);
        Date ngayKetThuc = dateFormat.parse(ngayKetThucStr);
        return hanhViMauRepository.findDsMauByHanhVi(hanhVi,ngayBatDau,ngayKetThuc);
    }

}
