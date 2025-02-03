package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.ApiResponseDTO;
import com.example.demo.dto.DepartmentDTO;

@FeignClient(name = "department-service", url = "http://localhost:8081") // Adjust the URL as needed
public interface APIClient {

    @GetMapping("/api/departments/{id}")
    ApiResponseDTO<DepartmentDTO> getDepartmentById(@PathVariable("id") Long id);
}





