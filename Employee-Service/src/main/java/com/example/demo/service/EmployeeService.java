package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

public interface EmployeeService {
    
    List<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO getEmployeeById(Long id);
    
    Employee createEmployee(Employee employee);
    
    EmployeeDTO updateEmployee(Long id, Employee updatedEmployee);
    
    void deleteEmployee(Long id);
    
    DepartmentDTO getDepartment(Long departmentId);
}




