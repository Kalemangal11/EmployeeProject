package com.employeeProject.Service;

import java.util.List;

import com.employeeProject.payload.EmployeeDto;

public interface EmpServiceI {

	//Create
	public EmployeeDto createEmp(EmployeeDto empDto);
	
	//Update
	public EmployeeDto updateEmp(EmployeeDto empDto, Integer eId);
	
	
	//Get Single Employee
	public EmployeeDto getSingleEmp(Integer eId);
	
	//Get All
	public List<EmployeeDto> getAllEmp();
	
	//Delete
	public void deleteEmp(Integer eId);
}
