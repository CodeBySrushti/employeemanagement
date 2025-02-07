package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class); // Logger declaration
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments"); // Logger usage
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        logger.info("Fetching department with ID: {}", id); // Logger usage
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public Department createDepartment(Department department) {
        logger.info("Creating new department: {}", department); // Logger usage
        validateDepartment(department);
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department updateDepartment) {
        logger.info("Updating department with ID: {}", id); // Logger usage
        Department existingDepartment = getDepartmentById(id);
        validateDepartment(updateDepartment);
        existingDepartment.setDepartmentName(updateDepartment.getDepartmentName());
        existingDepartment.setDepartmentDescription(updateDepartment.getDepartmentDescription());
        existingDepartment.setDepartmentCode(updateDepartment.getDepartmentCode());
        
        existingDepartment.setPhoneNumber(updateDepartment.getPhoneNumber());
        existingDepartment.setLocation(updateDepartment.getLocation());
        existingDepartment.setFloorNumber(updateDepartment.getFloorNumber());
        
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        logger.info("Deleting department with ID: {}", id); // Logger usage
        departmentRepository.deleteById(id);
    }

    private void validateDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        if (!hasText(department.getDepartmentName())) {
            throw new IllegalArgumentException("Department name cannot be empty");
        }
        if (!hasText(department.getDepartmentCode())) {
            throw new IllegalArgumentException("Department code cannot be empty");
        }
        // Add other validations as needed
    }

    private boolean hasText(String str) {
        return str != null && !str.trim().isEmpty();
    }
}

















