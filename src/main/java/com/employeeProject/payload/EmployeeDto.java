package com.employeeProject.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

	
	private Integer eId;
	
	private String eName;
	
	private Integer eAge;
	
	private String eDesignation;
}
