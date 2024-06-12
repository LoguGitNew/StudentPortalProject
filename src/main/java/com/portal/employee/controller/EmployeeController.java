package com.portal.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.employee.DTO.EmployeeDTO;
import com.portal.employee.DTO.ResponseAPIDTO;
import com.portal.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/allEmployees")
	public List<EmployeeDTO> fetchAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employee = employeeService.saveEmployee(employeeDTO);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/getEmployeeById/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
		try {
			if (employeeId == null) {
				throw new IllegalArgumentException("Employee id cannot be null");
			} else {
				EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
				return new ResponseEntity<>(employee, HttpStatus.OK);
			}
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getEmployeeByName/{employeeName}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable String employeeName) {
		try {
			List<EmployeeDTO> employeeList = employeeService.getEmployeeByName(employeeName);
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getEmployeeByCode/{employeeCode}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByCode(@PathVariable String employeeCode) {
		try {
			List<EmployeeDTO> employeeList = employeeService.getEmployeeByCode(employeeCode);
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getEmployeeMSById/{employeeId}")
	public ResponseEntity<ResponseAPIDTO> getEmployeeMSById(@PathVariable Long employeeId) {
		ResponseAPIDTO employee = employeeService.getEmployeeMSById(employeeId);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

}
