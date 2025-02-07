package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}
	
	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		System.out.println(department.getDepartmentName());
		System.out.println(department.getDepartmentCode());
		System.out.println(department.getDepartmentDescription());
		
		System.out.println(department.getPhoneNumber());
		System.out.println(department.getLocation());
		System.out.println(department.getFloorNumber());
		
		
		Department dept = departmentService.createDepartment(department);

		System.out.println(dept.getId());
		System.out.println(dept.getDepartmentName());
		System.out.println(dept.getDepartmentCode());
		System.out.println(dept.getDepartmentDescription());
		
		System.out.println(dept.getPhoneNumber());
		System.out.println(dept.getLocation());
		System.out.println(dept.getFloorNumber());
		
		return new ResponseEntity<>(dept, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		return  ResponseEntity.ok(departmentService.updateDepartment(id, department));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
