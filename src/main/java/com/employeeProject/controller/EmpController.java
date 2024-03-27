package com.employeeProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeProject.Service.EmpServiceI;
import com.employeeProject.constants.ApiConstants;
import com.employeeProject.payload.ApiResponse;
import com.employeeProject.payload.EmployeeDto;

@RestController
@RequestMapping("/empApi")
public class EmpController {

	@Autowired
	private EmpServiceI empServiceI;
	
	@PostMapping("/employee")
	public ResponseEntity<EmployeeDto> createEmp(@RequestBody EmployeeDto empDto) {
		EmployeeDto createdEmp = empServiceI.createEmp(empDto);
		return new  ResponseEntity<EmployeeDto>(createdEmp,HttpStatus.CREATED);
	}
	
	@PutMapping("/employee/{eId}")
	public ResponseEntity<EmployeeDto> updateEmp(@RequestBody EmployeeDto empDto,@PathVariable Integer eId) {
		EmployeeDto updatedEmp = empServiceI.updateEmp(empDto, eId);
		return new  ResponseEntity<EmployeeDto>(updatedEmp,HttpStatus.CREATED);
		}
	
	@GetMapping("/employee/{eId}")
	public ResponseEntity<EmployeeDto> getSingleEmp(@PathVariable Integer eId) {
		EmployeeDto singleEmp = empServiceI.getSingleEmp(eId);
		return new ResponseEntity<EmployeeDto>(singleEmp,HttpStatus.OK);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDto>> getAllEmp(){
		List<EmployeeDto> allEmp = empServiceI.getAllEmp();
		return new ResponseEntity<List<EmployeeDto>>(allEmp,HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{eId}")
	public ResponseEntity<ApiResponse> deleteEmp(@PathVariable Integer eId) {
		empServiceI.deleteEmp(eId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.EMPLOYEE_DELETED,true),HttpStatus.OK);
		}
}
