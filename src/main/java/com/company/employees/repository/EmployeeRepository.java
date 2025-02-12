package com.company.employees.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.company.employees.model.Employee;
import com.company.employees.model.EmployeeIndividualResponse;
import com.company.employees.model.EmployeeResponse;

@Repository
public class EmployeeRepository {
	@Autowired
	private final RestTemplate restTemplate;
	@Value("${endpoint}")
	private String BASE_URL;

    @Autowired
    public EmployeeRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	public List<Employee> getAllEmployees(){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cookie", "humans_21909=1");

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<EmployeeResponse> response = restTemplate.exchange(
				BASE_URL+"employees", HttpMethod.GET, requestEntity, EmployeeResponse.class);
		List<Employee> employees = response.getBody().getData();
		return employees;
	}
	
	public Employee getEmployeeById(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cookie", "humans_21909=1");

		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<EmployeeIndividualResponse> response = restTemplate.exchange(
				BASE_URL+"employee/"+id, HttpMethod.GET, requestEntity, EmployeeIndividualResponse.class);
		Employee employee = response.getBody().getData();
		return employee;
	}
}
