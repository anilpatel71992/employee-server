package com.employee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.employee.model.dto.EmployeeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	private String empName;
	
	private double salary;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = true, updatable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = true)
	private Date modifiedDate;
	
	@Column(insertable = true, updatable = false)
	private String createdBy;
	
	@Column(insertable = false, updatable = true)
	private String lastModifiedBy;
	
	public Employee(EmployeeDTO employeeDTO) {
		this.empName = employeeDTO.getEmpName();
		this.salary = employeeDTO.getSalary();
	}
	
	@PreUpdate
	public void setUpdatedAuditFields() {
		setModifiedDate(new Date());
		setLastModifiedBy("Anonymous");
	
	}
		
	@PrePersist
	public void setAuditFields() {
		setCreatedDate(new Date());
		setCreatedBy("Anonymous");
	}
}
