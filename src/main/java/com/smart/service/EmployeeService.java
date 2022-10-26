package com.smart.service;

import com.smart.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Long id);

    List<Employee> getByOrganizationId(Long orgId);
}
