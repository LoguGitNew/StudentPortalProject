package com.portal.employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIDTO {

	private EmployeeDTO employeeDto;
	private DepartmentDTO departmentDto;
}
