package com.seleniumexpress.employeeapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seleniumexpress.employeeapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
