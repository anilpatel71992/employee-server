package com.employee.model.dto;

import java.io.Serializable;

import com.employee.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

	private long empId;
	private String empName;
	private double salary;
	
	public EmployeeDTO(Employee employee) {
		 this.empId = employee.getEmpId();
		 this.empName = employee.getEmpName();
		 this.salary = employee.getSalary();
	}
}
