package com.employee.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.model.dto.EmployeeDTO;
import com.employee.service.EmployeeService;
import com.employee.utils.ApiResponse;
import com.employee.utils.AppUtils;
import com.employee.utils.Constant;

@RestController
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public ApiResponse addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println("Calling add API...");
		ApiResponse apiResponse = new ApiResponse();
		try {
			System.out.println(AppUtils.writeValueAsString(employeeDTO));
			
			Employee  employee = Employee.builder().empName(employeeDTO.getEmpName()).salary(employeeDTO.getSalary()).build();
			employee = employeeService.saveEmployee(employee);
			apiResponse.setResponse(new EmployeeDTO(employee));
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(Constant.FAILED);
			apiResponse.setError(e.getMessage());
		}
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/employee/fetch", method = RequestMethod.GET)
	public ApiResponse fetchEmployeeDetails(@RequestParam long empId) {
		System.out.println("Calling fetch API...");
		ApiResponse apiResponse = new ApiResponse();
		try {
			Employee employee = employeeService.getEmployeeById(empId);
			if(employee == null) {
				apiResponse.setStatus(Constant.FAILED);
				apiResponse.setError("Employee is not exist with this id");
			} else {
				apiResponse.setResponse(new EmployeeDTO(employee));
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(Constant.FAILED);
			apiResponse.setError(e.getMessage());
		}
		return apiResponse;
	}
}
