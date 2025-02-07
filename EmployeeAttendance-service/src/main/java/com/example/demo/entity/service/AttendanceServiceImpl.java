package com.example.demo.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeAttendance;
import com.example.demo.entity.dto.EmployeeAttendanceDTO;
import com.example.demo.entity.repository.AttendanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<EmployeeAttendanceDTO> getAllAttendances() {
        return attendanceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeAttendanceDTO getAttendanceById(Long id) {
        EmployeeAttendance attendance = attendanceRepository.findById(id).orElseThrow();
        return convertToDTO(attendance);
    }

    @Override
    public EmployeeAttendanceDTO createAttendance(EmployeeAttendanceDTO attendanceDTO) {
        EmployeeAttendance attendance = convertToEntity(attendanceDTO);
        return convertToDTO(attendanceRepository.save(attendance));
    }

    @Override
    public EmployeeAttendanceDTO updateAttendance(Long id, EmployeeAttendanceDTO attendanceDTO) {
        EmployeeAttendance existingAttendance = attendanceRepository.findById(id).orElseThrow();
        existingAttendance.setEmployeeId(attendanceDTO.getEmployeeId());
        existingAttendance.setDate(attendanceDTO.getDate());
        existingAttendance.setPresent(attendanceDTO.getPresent());
        return convertToDTO(attendanceRepository.save(existingAttendance));
    }

    @Override
    public void deleteAttendance(Long id) {
        EmployeeAttendance existingAttendance = attendanceRepository.findById(id).orElseThrow();
        attendanceRepository.delete(existingAttendance);
    }

    private EmployeeAttendanceDTO convertToDTO(EmployeeAttendance attendance) {
        EmployeeAttendanceDTO attendanceDTO = new EmployeeAttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setEmployeeId(attendance.getEmployeeId());
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setPresent(attendance.getPresent());
        return attendanceDTO;
    }

    private EmployeeAttendance convertToEntity(EmployeeAttendanceDTO attendanceDTO) {
        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setId(attendanceDTO.getId());
        attendance.setEmployeeId(attendanceDTO.getEmployeeId());
        attendance.setDate(attendanceDTO.getDate());
        attendance.setPresent(attendanceDTO.getPresent());
        return attendance;
    }
}










//public class AttendanceServiceImpl {
//
//}
