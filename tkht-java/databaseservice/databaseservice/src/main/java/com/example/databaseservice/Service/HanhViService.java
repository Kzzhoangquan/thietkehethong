package com.example.databaseservice.Service;

import com.example.databaseservice.Model.HanhVi;
import com.example.databaseservice.Repository.HanhViRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HanhViService {

    @Autowired
    private HanhViRepository hanhViRepository;

    public HanhVi findOrCreateHanhVi(HanhVi a) {
        return hanhViRepository.findByTen(a.getTen().trim())
                .orElseGet(() -> {
                    return hanhViRepository.save(a);
                });
    }
}