package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAll")
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
	    EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
	    return ResponseEntity.ok(employeeDTO);
	}
	
	
	@PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());
        System.out.println(employee.getEmail());
        
        System.out.println(employee.getAge());
        System.out.println(employee.getSalary());
        System.out.println(employee.getDate_of_joining());
        System.out.println(employee.getDepartmentId());

        Employee createdEmployee = employeeService.createEmployee(employee);

        System.out.println(createdEmployee.getId());
        System.out.println(createdEmployee.getFirstName());
        System.out.println(createdEmployee.getLastName());
        System.out.println(createdEmployee.getEmail());
        
        System.out.println(createdEmployee.getAge());
        System.out.println(createdEmployee.getSalary());
        System.out.println(createdEmployee.getDate_of_joining());
        
        System.out.println(createdEmployee.getDepartmentId());
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }



//	@PostMapping
//	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
//		Employee createdEmployee = employeeService.createEmployee(employee);
//		return ResponseEntity.ok(createdEmployee);
//	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, updatedEmployee);
		return ResponseEntity.ok(updatedEmployeeDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}