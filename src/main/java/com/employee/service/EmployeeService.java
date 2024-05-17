package com.employee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(long id) {
		return employeeRepository.findByEmpId(id);
	}
}
