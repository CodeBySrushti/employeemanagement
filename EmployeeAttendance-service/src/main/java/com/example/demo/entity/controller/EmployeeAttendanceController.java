package com.example.demo.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.dto.EmployeeAttendanceDTO;
import com.example.demo.entity.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/api/employee-attendances")
public class EmployeeAttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<EmployeeAttendanceDTO> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAttendanceDTO> getAttendanceById(@PathVariable Long id) {
        EmployeeAttendanceDTO attendanceDTO = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendanceDTO);
    }

    @PostMapping
    public EmployeeAttendanceDTO createAttendance(@RequestBody EmployeeAttendanceDTO attendanceDTO) {
        return attendanceService.createAttendance(attendanceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAttendanceDTO> updateAttendance(@PathVariable Long id, @RequestBody EmployeeAttendanceDTO attendanceDTO) {
        EmployeeAttendanceDTO updatedAttendance = attendanceService.updateAttendance(id, attendanceDTO);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
