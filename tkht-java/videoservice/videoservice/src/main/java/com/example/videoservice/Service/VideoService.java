package com.example.videoservice.Service;

import com.example.videoservice.Model.HanhVi;
import com.example.videoservice.Model.Mau;
import com.example.videoservice.Model.MoHinh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private RestTemplate restTemplate;

    private final PredictionStrategy predictionStrategy;

    @Autowired
    public VideoService(PyStrategy pyStrategy) {
        this.predictionStrategy = pyStrategy;
    }

    public List<MoHinh> getAllModels() {
        String dbApiUrl = "http://localhost:8082/database/models";
        MoHinh[] models = restTemplate.getForObject(dbApiUrl, MoHinh[].class);
        return Arrays.asList(models);
    }
    public List<HanhVi> predictAction(Mau mau, MoHinh moHinh) throws Exception {
        return predictionStrategy.predict(mau, moHinh);
    }


//    public List<HanhVi> predictAction(Mau mau, MoHinh moHinh) throws Exception {
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("mau", new ObjectMapper().writeValueAsString(mau));
//        body.add("moHinh", new ObjectMapper().writeValueAsString(moHinh));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//        String pythonApiUrl = "http://localhost:5000/predict/video";
//        ResponseEntity<Map> response = restTemplate.exchange(
//                pythonApiUrl,
//                HttpMethod.POST,
//                requestEntity,
//                Map.class
//        );
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody != null && responseBody.containsKey("actions")) {
//                List<Map<String, Object>> actions = (List<Map<String, Object>>) responseBody.get("actions");
//                List<HanhVi> hanhVis = actions.stream()
//                        .map(actionData -> new ObjectMapper().convertValue(actionData, HanhVi.class))
//                        .collect(Collectors.toList());
//
//                return hanhVis;
//            } else {
//                throw new Exception("Error: No actions found in response");
//            }
//        } else {
//            throw new Exception("Error: Failed to predict action, status: " + response.getStatusCode());
//        }
//    }


    public String saveToDatabase(Mau mau, MoHinh moHinh, List<HanhVi> hanhVis) throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("mau", Map.of(
                "videopath", mau.getVideopath()
        ));
        payload.put("moHinh", Map.of(
                "id", moHinh.getId(),
                "ten", moHinh.getTen(),
                "phienban", moHinh.getPhienban() != null ? moHinh.getPhienban() : "",
                "dochinhxac", moHinh.getDochinhxac() != null ? moHinh.getDochinhxac() : ""
        ));
        List<Map<String, Object>> hanhViList = hanhVis.stream()
                .map(hv -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("ten", hv.getTen());
                    return map;
                })
                .collect(Collectors.toList());
        payload.put("hanhVis", hanhViList);
        String dbApiUrl = "http://localhost:8082/database/save-result";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                dbApiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return "✅ Lưu dữ liệu thành công vào database.";
        } else {
            throw new Exception("❌ Lỗi lưu database — Status: " + response.getStatusCode());
        }
    }

}
