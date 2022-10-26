package com.smart.repository;

import com.smart.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    EmployeeDto getByEmployeeId(Long id);

    List<Employee> getAllByEmployeeIdIsNotNull();

    List<Employee> getByOrganizationId(Long orgId);
}
