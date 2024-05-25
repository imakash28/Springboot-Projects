package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.ExcelHelper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/Student/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file)
	{
		if(ExcelHelper.checkExcelFormat(file))
		{
			//true
			this.studentService.save(file);
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved to db"));
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File.");
	}
	
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return this.studentService.getAllStudents();
	}
	

}
