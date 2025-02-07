package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Department;

public interface DepartmentService {
	
	List<Department> getAllDepartments();
	
	Department getDepartmentById(Long id);
	
	Department createDepartment(Department department);
	
	Department updateDepartment(Long id, Department updateDepartment);
	
	void deleteDepartment(Long id);
	

}
