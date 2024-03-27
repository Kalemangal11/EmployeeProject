package com.employeeProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeProject.entity.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{

}
