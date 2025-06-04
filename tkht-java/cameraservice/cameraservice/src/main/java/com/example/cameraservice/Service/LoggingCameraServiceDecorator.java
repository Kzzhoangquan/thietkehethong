package com.example.cameraservice.Service;

import com.example.cameraservice.Model.HanhVi;
import com.example.cameraservice.Model.Mau;
import com.example.cameraservice.Model.MoHinh;

import java.util.List;
import java.util.stream.Collectors;

public class LoggingCameraServiceDecorator extends CameraService {
    private final CameraService wrappedService;

    public LoggingCameraServiceDecorator(CameraService wrappedService) {
        this.wrappedService = wrappedService;
        System.out.println("[LOG] LoggingCameraServiceDecorator initialized");
    }

    @Override
    public List<MoHinh> getAllModels() {
        System.out.println("[LOG] Fetching models from database");
        try {
            List<MoHinh> models = wrappedService.getAllModels();
            System.out.println("[LOG] Fetched " + models.size() + " models");
            return models;
        } catch (Exception e) {
            System.out.println("[LOG] Failed to fetch models: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<HanhVi> predictFromCamera(Mau mau, MoHinh moHinh) throws Exception {
        System.out.println("[LOG] Predicting for video: " + mau.getVideopath() + ", model: " + moHinh.getTen());
        try {
            List<HanhVi> result = wrappedService.predictFromCamera(mau, moHinh);
            System.out.println("[LOG] Prediction done: " + result.stream().map(HanhVi::getTen).collect(Collectors.joining(", ")));
            return result;
        } catch (Exception e) {
            System.out.println("[LOG] Prediction failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String saveToDatabase(Mau mau, MoHinh moHinh, List<HanhVi> hanhVis) throws Exception {
        System.out.println("[LOG] Saving to database for video: " + mau.getVideopath());
        try {
            String result = wrappedService.saveToDatabase(mau, moHinh, hanhVis);
            System.out.println("[LOG] Save completed: " + result);
            return result;
        } catch (Exception e) {
            System.out.println("[LOG] Save failed: " + e.getMessage());
            throw e;
        }
    }
}