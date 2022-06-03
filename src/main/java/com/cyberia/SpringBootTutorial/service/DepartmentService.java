package com.cyberia.SpringBootTutorial.service;

import com.cyberia.SpringBootTutorial.entity.Department;
import com.cyberia.SpringBootTutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long id) throws DepartmentNotFoundException;

    public Department fetchDepartmentByName(String name) throws DepartmentNotFoundException;

    public boolean existsById(Long id);

    public boolean existsByName(String name);

    public void removeDepartmentById(Long id) throws DepartmentNotFoundException;

    public Department updateDepartment(Long id, Department department) throws DepartmentNotFoundException;
}
