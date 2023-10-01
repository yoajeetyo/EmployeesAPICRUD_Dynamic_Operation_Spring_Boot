package com.aks.EmployeesAPICRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aks.EmployeesAPICRUD.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
