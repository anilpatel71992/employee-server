package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

//	private final EmployeeRepository employeeRepository;
//	
//	@Autowired
//	public EmployeeService(final EmployeeRepository employeeRepository) {
//	    this.employeeRepository = employeeRepository;
//	}
	
	@Autowired
	public EmployeeService() {
		//this.employeeRepository = null;
	}
	
	@Transactional
	public Employee saveEmployee(Employee employee) {
		return new Employee(); //return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(long id) {
		return new Employee();  //return employeeRepository.findByEmpId(id);
	}
}
