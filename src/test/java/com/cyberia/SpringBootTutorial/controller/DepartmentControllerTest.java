package com.cyberia.SpringBootTutorial.controller;

import com.cyberia.SpringBootTutorial.entity.Department;
import com.cyberia.SpringBootTutorial.error.DepartmentNotFoundException;
import com.cyberia.SpringBootTutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().name("AWQ").address("Bangalore").code("CE-07").id(1L).build();
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(department.getName()));
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder().name("AWQ").address("Bangalore").code("CE-07").build();

        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"AWQ\",\n" +
                                "    \"address\": \"Bangalore\",\n" +
                                "    \"code\": \"CE-07\"    \n" +
                                "}   "))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}