package com.portal.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.employee.DTO.DepartmentDTO;
import com.portal.employee.DTO.EmployeeDTO;
import com.portal.employee.DTO.ResponseAPIDTO;
import com.portal.employee.entity.Employee;
import com.portal.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	APIClient apiClient;

	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employeeList = employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOList = employeeList.stream()
				.map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
						employee.getEmployeeAddress(), employee.getEmployeeCode(), employee.getDepartmentId()))
				.toList();
		return employeeDTOList;
	}

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setEmployeeCode(employeeDTO.getEmployeeCode());
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
		employee.setDepartmentId(employeeDTO.getDepartmentId());

		Employee savedEmployee = employeeRepository.save(employee);

		EmployeeDTO savedEmployeeDTO = new EmployeeDTO(savedEmployee.getEmployeeId(), savedEmployee.getEmployeeName(),
				savedEmployee.getEmployeeAddress(), savedEmployee.getEmployeeCode(), savedEmployee.getDepartmentId());
		return savedEmployeeDTO;
	}

	public EmployeeDTO getEmployeeById(Long employeeId) {
		if (employeeId == null) {
			throw new IllegalArgumentException("Employee id cannot be null");
		} else {
			if (!employeeRepository.existsById(employeeId)) {
				throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
			} else {
				Employee employee = employeeRepository.findById(employeeId).get();
				EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
						employee.getEmployeeAddress(), employee.getEmployeeCode(), employee.getDepartmentId());
				return employeeDTO;
			}
		}

	}

	public List<EmployeeDTO> getEmployeeByName(String employeeName) {
		List<EmployeeDTO> employeeDTOList = null;
		if (employeeName == null || employeeName.isEmpty()) {
			throw new IllegalArgumentException("Employee name cannot be null or empty");
		} else {
			List<Employee> employeeList = employeeRepository.findByEmployeeName(employeeName);
			employeeDTOList = employeeList.stream()
					.map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
							employee.getEmployeeAddress(), employee.getEmployeeCode(), employee.getDepartmentId()))
					.toList();
		}

		return employeeDTOList;
	}

	public List<EmployeeDTO> getEmployeeByCode(String employeeCode) {
		if (employeeCode == null || employeeCode.isEmpty()) {
			throw new IllegalArgumentException("Employee code cannot be null or empty");
		} else {
			List<Employee> employeeList = employeeRepository.findByEmployeeCode(employeeCode);
			List<EmployeeDTO> employeeDTOList = employeeList.stream()
					.map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
							employee.getEmployeeAddress(), employee.getEmployeeCode(), employee.getDepartmentId()))
					.toList();
			return employeeDTOList;
		}
	}

	public ResponseAPIDTO getEmployeeMSById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		DepartmentDTO departmentDto = apiClient.getDepartment(employee.getDepartmentId());

		EmployeeDTO employeeDto = new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
				employee.getEmployeeAddress(), employee.getEmployeeCode(), employee.getDepartmentId());

		ResponseAPIDTO responseAPIDTO = new ResponseAPIDTO(employeeDto, departmentDto);
		return responseAPIDTO;
	}

}
