package com.example.databaseservice.Service;

import com.example.databaseservice.Model.KetQuaNhanDang;
import com.example.databaseservice.Model.MoHinh;
import com.example.databaseservice.Repository.KetQuaNhanDangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KetQuaNhanDangService {

    @Autowired
    private KetQuaNhanDangRepository ketQuaNhanDangRepository;

    public KetQuaNhanDang saveKetQuaNhanDang(KetQuaNhanDang kq) {
//        KetQuaNhanDang kq = new KetQuaNhanDang();
//        kq.setMohinh(moHinh);
//        kq.setNgaynhandang(new Date());
        return ketQuaNhanDangRepository.save(kq);
    }
}