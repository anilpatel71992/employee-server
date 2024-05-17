package com.employee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.employee.utils.AppUtils.Activity;

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
public class ActivityLog implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String actor, entityType;
	
	@Enumerated(EnumType.STRING)
	private Activity activity;
	
	@Column(columnDefinition="MediumText")
	private String oldValue, newValue, error;

	private Long entityId;
	
	private String apiRequest, requestStatus;
	
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
	
	public ActivityLog(String actor, Class entityType, Activity activity, String oldValue, String newValue,	Long entityId, String apiRequest, 
			String requestStatus, String error) {
		super();
		this.actor = actor;
		this.entityType = entityType.getSimpleName();
		this.activity = activity;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.entityId = entityId;
		this.apiRequest = apiRequest;
		this.requestStatus = requestStatus;
		this.error = error;
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
