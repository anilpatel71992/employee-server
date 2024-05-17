package com.employee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

	private long empId;
	private String empName;
	private double salary;
}
