package com.java.departmentservice.controller;


import com.java.departmentservice.client.CustomerClient;
import com.java.departmentservice.model.Department;
import com.java.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    CustomerClient customerClient;

    @PostMapping
    Department createDepartment(@RequestBody Department department) {
        departmentRepository.addDepartment(department);
        return department;
    }

    @GetMapping
    List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    Department getById(@PathVariable Long id) {
        return departmentRepository.findById(id);
    }

    //getting the all the employees related to deaprtmentId
    @GetMapping("/with-employees")
    List<Department> getEmployeesByDepartmentId() {
        //make a service call to the employee service to find all the employees
        //Rest Client or Web Client and use its exchange method for invoking API
        List<Department> listofDepartments = new ArrayList<>();
        listofDepartments = departmentRepository.findAll();

        listofDepartments.forEach(department ->
                department.setList(customerClient.getEmployeesByDepartmentId(department.getId())));
        return listofDepartments;
    }


}
