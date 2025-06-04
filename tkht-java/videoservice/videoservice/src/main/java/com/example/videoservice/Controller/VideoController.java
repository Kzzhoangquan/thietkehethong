package com.example.videoservice.Controller;

import com.example.videoservice.Model.HanhVi;
import com.example.videoservice.Model.Mau;
import com.example.videoservice.Model.MoHinh;
import com.example.videoservice.Service.VideoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/form")
    public String showVideoForm(HttpSession session, Model model) {
        List<MoHinh> models = (List<MoHinh>) session.getAttribute("models");
        if (models == null) {
            models = videoService.getAllModels();
            session.setAttribute("models", models);
        }
        model.addAttribute("models", models);
        return "videoform";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleUpload(@RequestParam("videoFile") MultipartFile videoFile,
                                               @RequestParam("selectedModelId") Long selectedModelId,
                                               HttpSession session) {
        try {
            List<MoHinh> models = (List<MoHinh>) session.getAttribute("models");
            MoHinh selectedModel = models.stream()
                    .filter(model -> model.getId().equals(selectedModelId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Mô hình không hợp lệ"));
            String timeStamp = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            String fileName = timeStamp + ".mp4";
            String uploadDir = "D:/uploads/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            File dest = new File(uploadDir + fileName);
            videoFile.transferTo(dest);
            Mau m = new Mau(dest.getAbsolutePath());
            List<HanhVi> lhv = videoService.predictAction(m, selectedModel);
            String ketqua = "";
            int kt = 0;
            for(HanhVi i : lhv){
                ketqua= ketqua + i.getTen()+",  ";
                if(i.getTen().equals("khong xac dinh")) kt = 1;
            }
            if(kt == 0) {
                String save = videoService.saveToDatabase(m, selectedModel, lhv);
                System.out.println("luu ket qua");
                ketqua = ketqua.substring(0,ketqua.length()-3);
            }
            else{
                ketqua = "Mô hình không nhận diện được hành động trong video";
            }
            return ResponseEntity.ok("Kết quả: " + ketqua);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi upload file.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





