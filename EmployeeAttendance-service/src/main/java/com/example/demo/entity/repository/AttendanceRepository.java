package com.example.demo.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EmployeeAttendance;

	public interface AttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
	}


