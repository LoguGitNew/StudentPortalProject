package com.portal.employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private Long employeeId;
	private String employeeName;
	private String employeeAddress;
	private String employeeCode;
	private Long departmentId;

}
