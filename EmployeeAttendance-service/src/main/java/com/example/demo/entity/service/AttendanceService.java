package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.dto.EmployeeAttendanceDTO;

public interface AttendanceService {
    List<EmployeeAttendanceDTO> getAllAttendances();
    EmployeeAttendanceDTO getAttendanceById(Long id);
    EmployeeAttendanceDTO createAttendance(EmployeeAttendanceDTO attendanceDTO);
    EmployeeAttendanceDTO updateAttendance(Long id, EmployeeAttendanceDTO attendanceDTO);
    void deleteAttendance(Long id);
    
}

