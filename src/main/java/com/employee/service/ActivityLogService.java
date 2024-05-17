package com.employee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.employee.model.ActivityLog;
import com.employee.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

	@Resource
	private ActivityLogRepository activityLogRepository;
	
	public ActivityLog createActivityLog(ActivityLog activityLog) {
		return activityLogRepository.save(activityLog);
	}
}
