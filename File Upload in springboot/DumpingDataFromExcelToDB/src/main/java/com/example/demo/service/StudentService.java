package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.ExcelHelper;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;

import lombok.experimental.Helper;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * In this class we are creating to methods
	 * 1.	public void save menthod which takes MultipartFile(Excel file) as an arguement and it converts data from excel file to list of student and save it.
	 * 
	 * 2.	public List<Student> getAllStudent() it will return all the list of Student as JSON
	 * */
	
	public void save(MultipartFile file) {
		try {
			//Here we are getting the list of the products
			List<Student> student=ExcelHelper.convertExcelTOListOfStudent(file.getInputStream()); // the convertExcelTOListOfStudent method taking parameter as inputstream and in the save method we are passing argument as MultipartFile file so that we will use file.getInputStream
			this.studentRepository.saveAll(student);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getAllStudents(){
		return this.studentRepository.findAll();
	}

}
