package com.example.thongkeservice.Service;

import com.example.thongkeservice.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThongKeService {

    @Autowired
    private RestTemplate restTemplate;

    public List<TKHanhVi> thongKeMauTheoHanhVi(String ngayBatDau, String ngayKetThuc) {
        String url = "http://localhost:8082/database/thongkemauhanhvi?ngayBatDau=" + ngayBatDau + "&ngayKetThuc=" + ngayKetThuc;

        ResponseEntity<List<TKHanhVi>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TKHanhVi>>() {}
        );

        return response.getBody();
    }


    public List<TKMoHinh> thongKeMauTheoMoHinh(String ngayBatDau, String ngayKetThuc) {
        String url = "http://localhost:8082/database/thongkemaumohinh?ngayBatDau=" + ngayBatDau + "&ngayKetThuc=" + ngayKetThuc;

        ResponseEntity<List<TKMoHinh>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TKMoHinh>>() {}
        );

        return response.getBody();
    }



    public List<Mau> layDsMauTheoHanhVi(HanhVi hanhvi, String ngayBatDau, String ngayKetThuc) {
        String url = "http://localhost:8082/database/dsmauhanhvi";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo Map để gửi dữ liệu JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("hanhvi", hanhvi);
        requestBody.put("ngayBatDau", ngayBatDau);
        requestBody.put("ngayKetThuc", ngayKetThuc);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<List<Mau>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Mau>>() {}
        );

        return response.getBody();
    }



    public List<Mau> layDsMauTheoMoHinh(MoHinh moHinh, String ngayBatDau, String ngayKetThuc) {
        String url = "http://localhost:8082/database/dsmaumohinh";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo Map để gửi dữ liệu JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("mohinh", moHinh);
        requestBody.put("ngayBatDau", ngayBatDau);
        requestBody.put("ngayKetThuc", ngayKetThuc);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<List<Mau>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Mau>>() {}
        );

        return response.getBody();
    }



}

