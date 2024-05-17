package com.employee.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public String addEmployee(@RequestBody Employee employee) {
		System.out.println("Calling add API...");
		try {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(employee));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Failed";
		}
		
		return "Saved";
	}
	
	@RequestMapping(value = "/employee/fetch", method = RequestMethod.GET)
	public String fetchEmployeeDetails(@RequestParam long empId) {
		System.out.println("Calling fetch API...");
		try {
			ObjectMapper mapper = new ObjectMapper();
			Employee employee = Employee.builder().empId(101).empName("Raj").salary(120).build();
			return mapper.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Failed";
		}
	}
}
