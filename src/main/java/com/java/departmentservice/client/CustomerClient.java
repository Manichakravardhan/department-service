package com.java.departmentservice.client;

import com.java.departmentservice.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface CustomerClient {

    @GetExchange("employee/department/{departmentId}")
    List<Employee> getEmployeesByDepartmentId(@PathVariable Long departmentId);

}
