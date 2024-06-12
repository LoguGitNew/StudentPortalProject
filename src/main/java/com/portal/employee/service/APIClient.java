package com.portal.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.portal.employee.DTO.DepartmentDTO;

@FeignClient(url = "http://localhost:9090", value = "DEPARTMENT")
public interface APIClient {
	
	@GetMapping("api/departments/{department-id}")
    DepartmentDTO getDepartment(@PathVariable("department-id") Long departmentId);
}
