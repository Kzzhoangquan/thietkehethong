package com.example.cameraservice.Service;

import com.example.cameraservice.Model.HanhVi;
import com.example.cameraservice.Model.Mau;
import com.example.cameraservice.Model.MoHinh;

import java.util.List;

public interface CameraServiceInterface {
    List<MoHinh> getAllModels();
    List<HanhVi> predictFromCamera(Mau mau, MoHinh moHinh) throws Exception;
    String saveToDatabase(Mau mau, MoHinh moHinh, List<HanhVi> hanhVis) throws Exception;
}