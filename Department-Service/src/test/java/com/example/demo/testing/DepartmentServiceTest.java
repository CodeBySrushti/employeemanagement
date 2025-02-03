package com.example.demo.testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentServiceImpl;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department(1L, "HR", "Human Resources", "HR01");
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(department);
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();
        assertEquals(1, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(1L);
        assertNotNull(result);
        assertEquals("HR", result.getDepartmentName());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateDepartment() {
        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.createDepartment(department);
        assertNotNull(result);
        assertEquals("HR", result.getDepartmentName());
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testUpdateDepartment() {
        Department updatedDepartment = new Department(1L, "Finance", "Finance Department", "FIN01");
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepository.save(department)).thenReturn(updatedDepartment);

        Department result = departmentService.updateDepartment(1L, updatedDepartment);
        assertNotNull(result);
        assertEquals("Finance", result.getDepartmentName());
        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentRepository).deleteById(1L);

        departmentService.deleteDepartment(1L);
        verify(departmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testValidateDepartment() {
        Department invalidDepartment = new Department(1L, "", "Description", "CODE01");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            departmentService.createDepartment(invalidDepartment);
        });

        assertEquals("Department name cannot be empty", exception.getMessage());
    }
}























//public class DepartmentServiceTest {
//
//}
