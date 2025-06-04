package com.example.videoservice.Service;

import com.example.videoservice.Model.HanhVi;
import com.example.videoservice.Model.Mau;
import com.example.videoservice.Model.MoHinh;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PyStrategy implements PredictionStrategy {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<HanhVi> predict(Mau mau, MoHinh moHinh) throws Exception {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mau", new ObjectMapper().writeValueAsString(mau));
        body.add("moHinh", new ObjectMapper().writeValueAsString(moHinh));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String pythonApiUrl = "http://localhost:5000/predict/video";
        ResponseEntity<Map> response = restTemplate.exchange(
                pythonApiUrl,
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("actions")) {
                List<Map<String, Object>> actions = (List<Map<String, Object>>) responseBody.get("actions");
                return actions.stream()
                        .map(actionData -> new ObjectMapper().convertValue(actionData, HanhVi.class))
                        .collect(Collectors.toList());
            } else {
                throw new Exception("Error: No actions found in response");
            }
        } else {
            throw new Exception("Error: Failed to predict action, status: " + response.getStatusCode());
        }
    }
}