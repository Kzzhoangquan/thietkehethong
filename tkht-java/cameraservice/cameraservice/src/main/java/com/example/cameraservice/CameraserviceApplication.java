package com.example.cameraservice;

import com.example.cameraservice.Service.CameraService;
import com.example.cameraservice.Service.LoggingCameraServiceDecorator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CameraserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CameraserviceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CameraService originalCameraService() {
		return new CameraService();
	}

	@Bean
	public CameraService cameraService(CameraService originalCameraService) {
		return new LoggingCameraServiceDecorator(originalCameraService);
	}
}