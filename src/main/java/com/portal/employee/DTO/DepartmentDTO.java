package com.portal.employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {	

	private Long departmentId;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	
}
