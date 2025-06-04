package com.example.databaseservice.Service;



import com.example.databaseservice.Model.Mau;
import com.example.databaseservice.Model.MoHinh;
import com.example.databaseservice.Model.TKMoHinh;
import com.example.databaseservice.Repository.MoHinhMauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MoHinhMauService {
    @Autowired
    private MoHinhMauRepository moHinhMauRepository;

    public List<TKMoHinh> thongKeMauTheoMoHinh(String ngayBatDauStr, String ngayKetThucStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ngayBatDau = sdf.parse(ngayBatDauStr);
            Date ngayKetThuc = sdf.parse(ngayKetThucStr);

            List<Object[]> rawData = moHinhMauRepository.countMauByMoHinh(ngayBatDau, ngayKetThuc);
            List<TKMoHinh> thongKe = new ArrayList<>();

            for (Object[] row : rawData) {
                Long id = (Long) row[0];
                String ten = (String) row[1];
                String phienban = (String) row[2];
                Float dochinhxac = (Float) row[3];
                Long soLuong = (Long) row[4];

                thongKe.add(new TKMoHinh(id, ten, phienban, dochinhxac, soLuong));
            }

            return thongKe;
        } catch (ParseException e) {
            throw new RuntimeException("Sai định dạng ngày yyyy-MM-dd");
        }
    }

    public List<Mau> layDanhSachMauTheoMoHinh(MoHinh moHinh,String ngayBatDauStr, String ngayKetThucStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayBatDau = dateFormat.parse(ngayBatDauStr);
        Date ngayKetThuc = dateFormat.parse(ngayKetThucStr);
        return moHinhMauRepository.findDsMauByMoHinh(moHinh,ngayBatDau,ngayKetThuc);
    }


}
