package com.example.databaseservice.Service;

import com.example.databaseservice.Model.Mau;
import com.example.databaseservice.Repository.MauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class MauService {

    @Autowired
    private MauRepository mauRepository;

    public Mau saveMau(Mau a) {
        a.setNgaytao(new Date());
        a.setLoaifile("mp4");
        return mauRepository.save(a);
    }

}
