package com.java.departmentservice.repository;


import com.java.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departmentList = new ArrayList<>();

    public List<Department> findAll() {
        return departmentList;
    }

    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    public Department findById(Long id) {
        return departmentList.stream().filter(department -> department.getId().equals(id)).findFirst().orElseThrow();
    }
}
