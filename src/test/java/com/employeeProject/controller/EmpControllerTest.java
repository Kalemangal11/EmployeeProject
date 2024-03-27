package com.employeeProject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employeeProject.Service.EmpServiceImpl;
import com.employeeProject.payload.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {EmpControllerTest.class} )
//@WebMvcTest(controllers = EmpController.class)
@AutoConfigureMockMvc
class EmpControllerTest {

	@Mock
	private EmpServiceImpl empServiceImpl;
	
	@InjectMocks
	private EmpController empController;
		
	@Autowired
	MockMvc mockMvc;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	private EmployeeDto employeeDto;
	private List<EmployeeDto> asList;
	
	 @BeforeEach
	    public void setup() {
		 mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
		 
		 employeeDto = EmployeeDto.builder().eId(1).eName("Mangal").eAge(32).eDesignation("Engineer").build();
//			employeeDto = new EmployeeDto();
//			employeeDto.setEId(1);
//	        employeeDto.setEName("John Doe");
//	        employeeDto.setEAge(30);
//	        employeeDto.setEDesignation("Engineer");
	        asList = Arrays.asList(
	                new EmployeeDto(1, "John Doe", 30, "Developer"),
	                new EmployeeDto(2, "Jane Smith", 35, "Manager")
	        );
	    }
	
	@Test
	void testCreateEmp() throws Exception {
				
	        when(empServiceImpl.createEmp(employeeDto)).thenReturn(employeeDto);
	        
	        String writeValueAsString = objectMapper.writeValueAsString(employeeDto);
	        
	        //Without input by mockMvc
	      //  mockMvc.perform(post("/empApi/employee")).andExpect(status().isCreated()).andDo(print()); 
	        
	        //With input by mockMvc
	        mockMvc.perform(post("/empApi/employee").content(writeValueAsString).contentType(MediaType.APPLICATION_JSON))
	        		.andExpect(status().isCreated()).andDo(print());
	        
//	        ResponseEntity<EmployeeDto> actualResult = empController.createEmp(employeeDto);
//	
//	        HttpStatusCode statusCode = actualResult.getStatusCode();
//	        System.out.println(statusCode);
//	        assertEquals(HttpStatus.CREATED, statusCode);
//	        assertEquals(employeeDto, actualResult.getBody());
	}

	@Test
	void testUpdateEmp() {
			
	        Integer id=1;
	        when(empServiceImpl.updateEmp(employeeDto,id)).thenReturn(employeeDto);
	        
	        ResponseEntity<EmployeeDto> actualResult = empController.updateEmp(employeeDto,id);
	
	        HttpStatusCode statusCode = actualResult.getStatusCode();
	        System.out.println(statusCode);
	        assertEquals(HttpStatus.CREATED, statusCode);
	        assertEquals(employeeDto, actualResult.getBody());
	        assertEquals(1, actualResult.getBody().getEId());
	}
	
	@Test
	void testGetSingleEmp() {
			
	        Integer id=1;
	        when(empServiceImpl.getSingleEmp(id)).thenReturn(employeeDto);
	        
	        ResponseEntity<EmployeeDto> actualResult = empController.getSingleEmp(id);
	
	        HttpStatusCode statusCode = actualResult.getStatusCode();
	        System.out.println(statusCode);
	        assertEquals(HttpStatus.OK, statusCode);
	        assertEquals(employeeDto, actualResult.getBody());
	}
	
	@Test
	void testGetAllEmp() {
			
	        when(empServiceImpl.getAllEmp()).thenReturn(asList);
	        
	        ResponseEntity<List<EmployeeDto>> allEmp = empController.getAllEmp();
	
	        HttpStatusCode statusCode = allEmp.getStatusCode();
	        System.out.println(statusCode);
	        assertEquals(HttpStatus.OK, statusCode);
	        assertEquals(asList, allEmp.getBody());
	}
	
//	@Test
//	void testDeleteEmp() {
//			
//	        when(empServiceImpl.deleteEmp(1)).thenReturn(employeeDto);
//	        
//	       empController.deleteEmp(1);
//	
//	       verify(null, null)
//	}
}
