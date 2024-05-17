package com.employee.validation;

import org.apache.commons.lang3.StringUtils;

import com.employee.model.dto.EmployeeDTO;
import com.employee.utils.ApiResponse;
import com.employee.utils.Constant;

public class EmployeeValidation {

	public static ApiResponse addEmployeeValidation(EmployeeDTO employee, ApiResponse apiResponse) {
		if(employee == null) {
			apiResponse.setError("Invalid Request");
		}
		if(StringUtils.isBlank(employee.getEmpName()) || !isAlpha(employee.getEmpName())) {
			apiResponse.setError("Invalid Employee Name");
		}
		if(employee.getSalary() <= 0) {
			apiResponse.setError("Invalid Employee Salary");
		}
		if(!StringUtils.isBlank(apiResponse.getError())) {
			apiResponse.setStatus(Constant.FAILED);
		}
		return apiResponse;
	}
	
	public static ApiResponse fetchEmployeeValidation(long id, ApiResponse apiResponse) {
		
		if(id <= 0) {
			apiResponse.setError("Invalid Employee Id");
		}
		if(!StringUtils.isBlank(apiResponse.getError())) {
			apiResponse.setStatus(Constant.FAILED);
		}
		return apiResponse;
	}
	
	public static boolean isAlpha(String val) {
	    return val.matches("[a-zA-Z ]+");
	}
}
