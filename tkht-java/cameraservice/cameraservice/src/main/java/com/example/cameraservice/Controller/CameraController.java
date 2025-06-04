package com.example.cameraservice.Controller;
import com.example.cameraservice.Model.MoHinh;
import com.example.cameraservice.Service.CameraFacade;
import com.example.cameraservice.Service.CameraService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    private CameraService cameraService;

    @Autowired
    private CameraFacade cameraFacade;

    @GetMapping("/form")
    public String showCameraForm(HttpSession session, Model model) {
        List<MoHinh> models = (List<MoHinh>) session.getAttribute("models");
        if (models == null) {
            models = cameraService.getAllModels();
            session.setAttribute("models", models);
        }
        model.addAttribute("models", models);
        return "cameraform";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleCameraUpload(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("selectedModelId") Long modelId,
                                                     HttpSession session) {
        try {
            String result = cameraFacade.processCameraUpload(file, modelId, session);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi upload file từ camera.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
//    @PostMapping("/upload")
//    public ResponseEntity<String> handleCameraUpload(@RequestParam("file") MultipartFile file,
//                                                     @RequestParam("selectedModelId") Long modelId,
//                                                     HttpSession session) {
//        try {
//            List<MoHinh> models = (List<MoHinh>) session.getAttribute("models");
//            if (models == null) {
//                models = cameraService.getAllModels();
//                session.setAttribute("models", models);
//            }
//            MoHinh selectedModel = models.stream()
//                    .filter(model -> model.getId().equals(modelId))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("Mô hình không hợp lệ"));
//            String fileName = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + "_cam.mp4";
//            String tempPath = "D:/uploads/" + fileName;
//            File tempFile = new File(tempPath);
//            file.transferTo(tempFile);
//            Mau m =new Mau(tempPath);
//            List<HanhVi> lhv = cameraService.predictFromCamera(m,selectedModel);
//            String ketqua = "";
//            int kt = 0;
//            for(HanhVi i : lhv){
//                ketqua=ketqua+i.getTen()+" ";
//                if(i.getTen().equals("khong xac dinh")) kt = 1;
//            }
//            System.out.println(ketqua);
//            if (kt == 0) {
//                String save = cameraService.saveToDatabase(m, selectedModel, lhv);
//                System.out.println("Luu ket qua");
//                return ResponseEntity.ok("Phát hiện hành vi: " + ketqua);
//            } else {
//                tempFile.delete();
//                return ResponseEntity.ok("Không phát hiện hành vi");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Lỗi upload file từ camera.");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


