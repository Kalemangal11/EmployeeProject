package com.employeeProject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employeeProject.Service.EmpServiceImpl;
import com.employeeProject.payload.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {EmpControllerTest2.class} )
@AutoConfigureMockMvc
class EmpControllerTest2 {


    @InjectMocks
    private EmpController empController;

    @Mock
    private EmpServiceImpl empService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {  // setUp() OR init() we can take as per industry standard
        mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
    }
	
	
	 @Test
	 public void testCreateEmp() throws Exception {
	     // Mock the service method
	     EmployeeDto empDto = new EmployeeDto(1,"Mangal",30,"Engineer"); // Create a sample employee DTO
	     EmployeeDto createdEmpDto = new EmployeeDto(1,"Mangal",30,"Engineer"); // Create a sample created employee DTO
	     when(empService.createEmp(empDto)).thenReturn(createdEmpDto);

	     // Convert object to JSON string
	     ObjectMapper objectMapper = new ObjectMapper();
	     String empDtoJson = objectMapper.writeValueAsString(empDto);

	     // Perform the POST request
	     mockMvc.perform(post("/empApi/employee")
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(empDtoJson))
	             .andExpect(status().isCreated()).andDo(print());
	            // .andExpect(MockMvcResultMatchers.jsonPath("$.eId").exists()); // Assuming eId is a field in EmployeeDto
	 }	     
	 
	 @Test
	 public void testGetSingleEmpPositive() throws Exception {
		 EmployeeDto empDto = new EmployeeDto(1,"Mangal",30,"Engineer"); // Create a sample employee DTO
	     EmployeeDto createdEmpDto = new EmployeeDto(1,"Mangal",30,"Engineer"); // Create a sample created employee DTO
	     Integer id=1;
	     when(empService.getSingleEmp(id)).thenReturn(createdEmpDto);
	     
	     mockMvc.perform(get("/empApi/employee/{eId}",id))
	     .andExpect(status().isOk());
//	     .andExpect(MockMvcResultMatchers.jsonPath("x.eId").value(1))
//	     .andExpect(MockMvcResultMatchers.jsonPath("x.eName").value("Mangal"))
//	     .andExpect(MockMvcResultMatchers.jsonPath("x.eAge").value(30))
//	     .andExpect(MockMvcResultMatchers.jsonPath("x.eDesignation").value("Engineer"))
//	     .andDo(print());

	     
	 }
	 
	 @ParameterizedTest
	 @CsvFileSource(resources="/EmployeeDB TableFromDB.csv")
	 public void testGetAllEmp(List<EmployeeDto> input,String expected) throws Exception {

//		 List<EmployeeDto> asList = Arrays.asList(
//	                new EmployeeDto(1, "John Doe", 30, "Developer"),
//	                new EmployeeDto(2, "Jane Smith", 35, "Manager");
				 
				 when(empService.getAllEmp()).thenReturn(input);
	     
				 
	 
	 
}
}