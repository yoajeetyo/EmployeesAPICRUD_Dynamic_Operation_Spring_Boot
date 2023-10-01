package com.aks.EmployeesAPICRUD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aks.EmployeesAPICRUD.model.Employee;
import com.aks.EmployeesAPICRUD.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "Employee created in Database";
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList :: add);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if(emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if(emp.isPresent()) {
			Employee existEmployee =emp.get();
			existEmployee.setEmp_age(employee.getEmp_age());
			existEmployee.setEmp_city(employee.getEmp_city());
			existEmployee.setEmp_name(employee.getEmp_name());
			existEmployee.setEmp_salary(employee.getEmp_salary());
			employeeRepository.save(existEmployee);
			return "Employee details against id "+empid+" updated.";
		}
		else {
			return "Employee details does not exist for empid "+empid;
		}
	}
	
	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeById(@PathVariable long empid) {
		employeeRepository.deleteById(empid);
		return "Employee deleted Successfully";
	}
	
	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		employeeRepository.deleteAll();
		return "All Employee deleted Successfully !!!";
	}
}
