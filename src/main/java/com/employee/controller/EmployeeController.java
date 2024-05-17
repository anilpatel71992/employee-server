package com.employee.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.dto.EmployeeDTO;
import com.employee.utils.ApiResponse;
import com.employee.utils.AppUtils;
import com.employee.utils.Constant;

@RestController
public class EmployeeController {

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public ApiResponse addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println("Calling add API...");
		ApiResponse apiResponse = new ApiResponse();
		try {
			System.out.println(AppUtils.writeValueAsString(employeeDTO));
			apiResponse.setResponse("Employee Details are saved!!");
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
			EmployeeDTO employeeDTO = EmployeeDTO.builder().empId(101).empName("Raj").salary(120).build();
			apiResponse.setResponse(AppUtils.writeValueAsString(employeeDTO));
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(Constant.FAILED);
			apiResponse.setError(e.getMessage());
		}
		return apiResponse;
	}
}
