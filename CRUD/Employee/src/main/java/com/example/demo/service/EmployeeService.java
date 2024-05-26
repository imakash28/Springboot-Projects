package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.UpdateEmployeeDto;
import com.example.demo.entity.Employee;

public interface EmployeeService {

	ResponseEntity<String> addEmployee(EmployeeDto employeeDto);

	ResponseEntity<List> getAllEmployeeList();

	ResponseEntity<String> updateEmployeeDetails(UpdateEmployeeDto updateEmployeeDto);

	ResponseEntity<String> updateStatus(String id);

	Optional<Employee> findById(String id);



}