package com.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.employee.model.dto.EmployeeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long empId;
	private String empName;
	private double salary;
	
	public Employee(EmployeeDTO employeeDTO) {
		this.empName = employeeDTO.getEmpName();
		this.salary = employeeDTO.getSalary();
	}
}
