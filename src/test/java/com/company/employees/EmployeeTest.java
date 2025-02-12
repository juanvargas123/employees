package com.company.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.company.employees.model.Employee;
import com.company.employees.model.EmployeeIndividualResponse;
import com.company.employees.model.EmployeeResponse;
import com.company.employees.repository.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepository(restTemplate);
    }

    @Test
    void testGetAllEmployees() {
    	Employee mockEmployee = new Employee(1, "John Doe", 50000, 30, "");
        EmployeeResponse mockResponse = new EmployeeResponse();
        mockResponse.setData(List.of(mockEmployee));

        ResponseEntity<EmployeeResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(EmployeeResponse.class)))
                .thenReturn(responseEntity);

        List<Employee> employees = employeeRepository.getAllEmployees();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertEquals(1, employees.get(0).getId());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
    	 Employee mockEmployee = new Employee(1, "John Doe", 50000, 30, "");
         EmployeeIndividualResponse mockResponse = new EmployeeIndividualResponse();
         mockResponse.setData(mockEmployee);

         ResponseEntity<EmployeeIndividualResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

         when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(EmployeeIndividualResponse.class)))
                 .thenReturn(responseEntity);

         Employee employee = employeeRepository.getEmployeeById(1);

         assertNotNull(employee);
         assertEquals(1, employee.getId());
         assertEquals("John Doe", employee.getName());
         assertEquals(50000, employee.getSalary());
    }
}