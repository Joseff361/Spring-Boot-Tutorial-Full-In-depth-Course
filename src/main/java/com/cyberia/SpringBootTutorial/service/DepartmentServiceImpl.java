package com.cyberia.SpringBootTutorial.service;

import com.cyberia.SpringBootTutorial.entity.Department;
import com.cyberia.SpringBootTutorial.error.DepartmentNotFoundException;
import com.cyberia.SpringBootTutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }

        return department.get();
    }

    @Override
    public Department fetchDepartmentByName(String name) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findByName(name);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }

        return department.get();
    }

    @Override
    public boolean existsById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }

    @Override
    public void removeDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }

        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Long id, Department department) throws DepartmentNotFoundException {
        if (departmentRepository.findById(id).isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }

        Department departmentBD = departmentRepository.findById(id).get();

        if (Objects.nonNull(department.getName()) && !department.getName().trim().equalsIgnoreCase("")) {
            departmentBD.setName(department.getName());
        }

        if (Objects.nonNull(department.getAddress()) && !department.getAddress().trim().equalsIgnoreCase("")) {
            departmentBD.setAddress(department.getAddress());
        }

        if (Objects.nonNull(department.getCode()) && !department.getCode().trim().equalsIgnoreCase("")) {
            departmentBD.setCode(department.getCode());
        }

        return departmentRepository.save(departmentBD);
    }
}
