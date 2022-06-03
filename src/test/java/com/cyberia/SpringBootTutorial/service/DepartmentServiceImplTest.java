package com.cyberia.SpringBootTutorial.service;

import com.cyberia.SpringBootTutorial.entity.Department;
import com.cyberia.SpringBootTutorial.error.DepartmentNotFoundException;
import com.cyberia.SpringBootTutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().name("IT").address("Ahmedabad").code("IT-06").id(1L).build();

        Mockito.when(departmentRepository.findByName("IT")).thenReturn(Optional.ofNullable(department));
    }

    @Test
    @DisplayName("Get data based on valid department name")
    //@Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getName());
    }
}
