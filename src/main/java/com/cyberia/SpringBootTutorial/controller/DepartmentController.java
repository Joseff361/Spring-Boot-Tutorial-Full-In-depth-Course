package com.cyberia.SpringBootTutorial.controller;

import com.cyberia.SpringBootTutorial.dto.Message;
import com.cyberia.SpringBootTutorial.entity.Department;
import com.cyberia.SpringBootTutorial.error.DepartmentNotFoundException;
import com.cyberia.SpringBootTutorial.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> fetchDepartmentList() {
        return new ResponseEntity<>(departmentService.fetchDepartmentList(), HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> fetchDepartmentById(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        LOGGER.info(String.valueOf(id));
        return new ResponseEntity<>(departmentService.fetchDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/departments/name/{name}")
    public ResponseEntity<Department> fetchDepartmentByName(@PathVariable String name) throws DepartmentNotFoundException {
        return new ResponseEntity<>(departmentService.fetchDepartmentByName(name), HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<?> saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info(department.toString());
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.OK);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) throws DepartmentNotFoundException {
        return new ResponseEntity<>(departmentService.updateDepartment(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") Long id) throws DepartmentNotFoundException {
        departmentService.removeDepartmentById(id);
        return new ResponseEntity<>(new Message("Department deleted"), HttpStatus.OK);
    }
}
