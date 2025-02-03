package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dto.ApiResponseDTO;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class); // Logger declaration
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private APIClient apiClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees"); // Logger usage
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>(); 
        
        for(Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id); // Logger usage
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        return employeeDTO;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Creating new employee: {}", employee); // Logger usage
        validateEmployee(employee);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(savedEmployee.getId());
        employeeDTO.setFirstName(savedEmployee.getFirstName());
        employeeDTO.setLastName(savedEmployee.getLastName());
        employeeDTO.setEmail(savedEmployee.getEmail());
        return savedEmployee;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, Employee updatedEmployee) {
        logger.info("Updating employee with ID: {}", id); // Logger usage
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        validateEmployee(updatedEmployee);
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        
        Employee savedEmployee = employeeRepository.save(existingEmployee);
        
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(savedEmployee.getId());
        employeeDTO.setFirstName(savedEmployee.getFirstName());
        employeeDTO.setLastName(savedEmployee.getLastName());
        employeeDTO.setEmail(savedEmployee.getEmail());
        
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id); // Logger usage
        employeeRepository.deleteById(id);
    }

    @Override
    public DepartmentDTO getDepartment(Long departmentId) {
        logger.info("Fetching department with ID: {}", departmentId); // Logger usage
        ApiResponseDTO<DepartmentDTO> response = apiClient.getDepartmentById(departmentId);
        if ("success".equals(response.getStatus())) {
            return response.getData();
        } else {
            throw new RuntimeException("Failed to fetch department: " + response.getMessage());
        }
    }

    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new ValidationException("Employee cannot be null");
        }
        if (!StringUtils.hasText(employee.getFirstName())) {
            throw new ValidationException("First name cannot be empty");
        }
        if (!StringUtils.hasText(employee.getLastName())) {
            throw new ValidationException("Last name cannot be empty");
        }
        if (!StringUtils.hasText(employee.getEmail())) {
            throw new ValidationException("Email cannot be empty");
        }
        // Add other validations as needed
    }
}


