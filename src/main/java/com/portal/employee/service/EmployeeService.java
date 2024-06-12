package com.portal.employee.service;

import java.util.List;

import com.portal.employee.DTO.EmployeeDTO;
import com.portal.employee.DTO.ResponseAPIDTO;

public interface EmployeeService {
	
	List<EmployeeDTO> getAllEmployees();
	
	EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Long employeeId);
	
	List<EmployeeDTO> getEmployeeByName(String employeeName);
	
	List<EmployeeDTO> getEmployeeByCode(String employeeCode);	
	
	ResponseAPIDTO getEmployeeMSById(Long employeeId);

}
