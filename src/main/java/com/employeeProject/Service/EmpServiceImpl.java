package com.employeeProject.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeProject.entity.Employee;
import com.employeeProject.exception.ResourceNotFountException;
import com.employeeProject.payload.EmployeeDto;
import com.employeeProject.repo.EmpRepo;

@Service
public class EmpServiceImpl implements EmpServiceI {

    private final EmpRepo empRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public EmpServiceImpl(EmpRepo empRepo, ModelMapper modelMapper) {
        this.empRepo = empRepo;
        this.modelMapper = modelMapper;
    }
	
	@Override
	public EmployeeDto createEmp(EmployeeDto empDto) {
		Employee emp = modelMapper.map(empDto, Employee.class);
		Employee savedEmp = empRepo.save(emp);
		EmployeeDto empDtos = modelMapper.map(savedEmp, EmployeeDto.class);
		return empDtos;
	}

	@Override
	public EmployeeDto updateEmp(EmployeeDto empDto, Integer eId) {
		empRepo.findById(eId).orElseThrow(()->new ResourceNotFountException("Employee","empId",eId));
		Employee emp = modelMapper.map(empDto, Employee.class);
		emp.setEId(eId);
		emp.setEName(empDto.getEName());
		emp.setEAge(empDto.getEAge());
		emp.setEDesignation(empDto.getEDesignation());
		Employee updatedEmp = empRepo.save(emp);
		EmployeeDto employeeDto = modelMapper.map(updatedEmp, EmployeeDto.class);
		return employeeDto;
	}

	@Override
	public EmployeeDto getSingleEmp(Integer eId) {
		 Employee employee = empRepo.findById(eId).orElseThrow(()->new ResourceNotFountException("Employee","empId",eId));
		 EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getAllEmp() {
		 List<Employee> findAll = empRepo.findAll();
		 List<EmployeeDto> collect = findAll.stream().map(emp->modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteEmp(Integer eId) {
		Employee employee = empRepo.findById(eId).orElseThrow(()->new ResourceNotFountException("Employee","empId",eId));
		empRepo.delete(employee);
		}


}
