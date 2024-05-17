package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.ActivityLog;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long>{

}
