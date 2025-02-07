package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;

@SpringBootTest
class EmployeeServiceApplicationTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(1L);
        assertEquals("John", employeeDTO.getFirstName());
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);
        assertEquals("John", createdEmployee.getFirstName());
    }

    @Test
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        Employee updatedEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(1L, updatedEmployee);
        assertEquals("John", updatedEmployeeDTO.getFirstName());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
