package com.employeeProject.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;
import org.modelmapper.ModelMapper;

import org.springframework.boot.test.context.SpringBootTest;

import com.employeeProject.entity.Employee;
import com.employeeProject.payload.EmployeeDto;
import com.employeeProject.repo.EmpRepo;


@SpringBootTest
class EmpServiceImplTest {


    @Mock
    private EmpRepo empRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmpServiceImpl empService;

    
    private EmployeeDto employeeDto;
    private Employee employee;
    private List<Employee> employeeList;

    
    @BeforeEach
    public void setup() {
        employeeDto = new EmployeeDto();
        employeeDto.setEId(1);
        employeeDto.setEName("John Doe");
        employeeDto.setEAge(30);
        employeeDto.setEDesignation("Engineer");

        employee = new Employee();
        employee.setEId(1);
        employee.setEName("John Doe");
        employee.setEAge(30);
        employee.setEDesignation("Engineer");

        employeeList = new ArrayList<>();
        employeeList.add(employee);
    }

    @Test
    public void testCreateEmp() {
        // Arrange
        when(modelMapper.map(employeeDto, Employee.class)).thenReturn(employee);
        when(empRepo.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = empService.createEmp(employeeDto);

        // Assert
        assertEquals(employeeDto, result);
    }
    
    @Test
    public void testUpdateEmp() {
        // Arrange
        when(empRepo.findById(1)).thenReturn(Optional.of(employee));
        when(empRepo.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        employee.setEId(1);
        employee.setEName(employeeDto.getEName());
        employee.setEAge(employeeDto.getEAge());
        employee.setEDesignation(employeeDto.getEDesignation());
		Employee updatedEmp = empRepo.save(employee);
		
		EmployeeDto employeeDto = modelMapper.map(updatedEmp, EmployeeDto.class);
        // Act
        EmployeeDto result = empService.updateEmp(employeeDto, 1);

        // Assert
        assertEquals(employeeDto.getEName(), result.getEName());
       // assertEquals(employeeDto.getEName(), result.getEName());
    }
    
    @Test
    public void testGetSingleEmp() {
        // Arrange
        when(empRepo.findById(1)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        // Act
        EmployeeDto result = empService.getSingleEmp(1);

        // Assert
        assertEquals(employeeDto, result);
    }

    @Test
    public void testGetAllEmp() {
        // Arrange
        when(empRepo.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        // Act
        List<EmployeeDto> result = empService.getAllEmp();

        // Assert
        assertEquals(1, result.size());
        assertEquals(employeeDto, result.get(0));
    }

    @Test
    public void testDeleteEmp() {
        // Arrange
        when(empRepo.findById(1)).thenReturn(Optional.of(employee));

        // Act
        empService.deleteEmp(1);

        // Assert
        verify(empRepo, times(1)).delete(employee);
    }

	private VerificationMode times(int i) {
		// TODO Auto-generated method stub
		return null;
	}


}
 