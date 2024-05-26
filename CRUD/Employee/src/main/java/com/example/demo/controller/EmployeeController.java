package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.UpdateEmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("/add")
	public ResponseEntity<String> addEmployee(@RequestBody  EmployeeDto employeeDto){
		return employeeService.addEmployee(employeeDto);
}
	
	@GetMapping("/get-all-employee")
	public ResponseEntity<List>getAllEmployeeList(){
		return employeeService.getAllEmployeeList();
	}
	
	@PutMapping("/update-employee")
	public ResponseEntity<String>updateEmployeeDetails(@RequestBody UpdateEmployeeDto updateEmployeeDto){
		return employeeService.updateEmployeeDetails(updateEmployeeDto);
	}
	
	@DeleteMapping("/delete-employee")
	public ResponseEntity<String>updateStatus(@RequestParam String id){
		return employeeService.updateStatus(id);       
	}
	
	@GetMapping("/get-one-employee")
	public Optional<Employee> findById(@RequestParam String id){
		return employeeService.findById(id);
	}
	
}