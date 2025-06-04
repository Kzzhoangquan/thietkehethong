package com.example.cameraservice.Service;

import com.example.cameraservice.Model.HanhVi;
import com.example.cameraservice.Model.Mau;
import com.example.cameraservice.Model.MoHinh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Component
public class CameraFacade {
    @Autowired
    private CameraService cameraService;

    public String processCameraUpload(MultipartFile file, Long modelId, HttpSession session) throws Exception {
        List<MoHinh> models = (List<MoHinh>) session.getAttribute("models");
        if (models == null) {
            models = cameraService.getAllModels();
            session.setAttribute("models", models);
        }

        MoHinh selectedModel = models.stream()
                .filter(model -> model.getId().equals(modelId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mô hình không hợp lệ"));

        String fileName = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + "_cam.mp4";
        String tempPath = "D:/uploads/" + fileName;
        File tempFile = new File(tempPath);
        file.transferTo(tempFile);

        Mau mau = new Mau(tempPath);
        List<HanhVi> hanhVis = cameraService.predictFromCamera(mau, selectedModel);

        String ketqua = "";
        boolean kt = false;
        for (HanhVi i : hanhVis) {
            ketqua= ketqua + i.getTen()+",  ";
            if (i.getTen().equals("khong xac dinh")) {
                kt = true;
            }
        }
        if (!kt) {
//            String saveResult = cameraService.saveToDatabase(mau, selectedModel, hanhVis);
            System.out.println("Lưu kết quả");
            ketqua = ketqua.substring(0,ketqua.length()-3);
            return "Phát hiện hành vi: " + ketqua;
        } else {
            tempFile.delete();
            return "Không phát hiện hành vi";
        }
    }
}