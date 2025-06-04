package com.example.databaseservice.Service;

import com.example.databaseservice.Model.MoHinh;
import com.example.databaseservice.Repository.MoHinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoHinhService {

    @Autowired
    private MoHinhRepository mohinhRepository;

    public List<MoHinh> getAllMohinh() {
        return mohinhRepository.findAll();
    }
}
