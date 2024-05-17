package com.employee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.ActivityLog;
import com.employee.model.Employee;
import com.employee.model.dto.EmployeeDTO;
import com.employee.service.ActivityLogService;
import com.employee.service.EmployeeService;
import com.employee.utils.ApiResponse;
import com.employee.utils.AppUtils;
import com.employee.utils.AppUtils.Activity;
import com.employee.utils.Constant;
import com.employee.validation.EmployeeValidation;

@RestController
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private ActivityLogService activityLogService;

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public ApiResponse addEmployee(@RequestBody(required = true) EmployeeDTO employeeDTO, HttpServletRequest request) {
		System.out.println("Calling add API...");
		ApiResponse apiResponse = new ApiResponse();
		String newVal = null;
		Long entityId = null;
		try {
			System.out.println(AppUtils.writeValueAsString(employeeDTO));
			if(Constant.SUCCESS.equals(EmployeeValidation.addEmployeeValidation(employeeDTO, apiResponse).getStatus())) {
				Employee employee = new Employee(employeeDTO);
				employee = employeeService.saveEmployee(employee);
				apiResponse.setResponse(new EmployeeDTO(employee));
				newVal = AppUtils.writeValueAsString(employee);
				entityId = employee.getEmpId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(Constant.FAILED);
			apiResponse.setError(e.getMessage());
		}
		
		activityLogService.createActivityLog(new ActivityLog("Anonymous", Employee.class, Activity.Add, null, newVal, entityId, request.getServletPath(), apiResponse.getStatus(), apiResponse.getError()));
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/employee/fetch", method = RequestMethod.GET)
	public ApiResponse fetchEmployeeDetails(@RequestParam(required = true) long empId, HttpServletRequest request) {
		System.out.println("Calling fetch API...");
		ApiResponse apiResponse = new ApiResponse();
		try {
			if(Constant.SUCCESS.equals(EmployeeValidation.fetchEmployeeValidation(empId, apiResponse).getStatus())) {
				Employee employee = employeeService.getEmployeeById(empId);
				if(employee == null) {
					apiResponse.setStatus(Constant.FAILED);
					apiResponse.setError("Employee is not exist with this id");
				} else {
					apiResponse.setResponse(new EmployeeDTO(employee));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(Constant.FAILED);
			apiResponse.setError(e.getMessage());
		}
		
		activityLogService.createActivityLog(new ActivityLog("Anonymous", Employee.class, Activity.Fetch, null, null, empId, request.getServletPath(), apiResponse.getStatus(), apiResponse.getError()));
		
		return apiResponse;
	}
}
