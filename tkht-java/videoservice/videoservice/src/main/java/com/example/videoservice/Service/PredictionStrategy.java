package com.example.videoservice.Service;

import com.example.videoservice.Model.HanhVi;
import com.example.videoservice.Model.Mau;
import com.example.videoservice.Model.MoHinh;

import java.util.List;

public interface PredictionStrategy {
    List<HanhVi> predict(Mau mau, MoHinh moHinh) throws Exception;
}