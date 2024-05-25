package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Student")
public class Student {

	@Id
	private String id;
	private String name;
	private String branch;
	private String Roll_No;
	
	public Student() {
		
	}
	
	public Student(String id, String name, String branch, String roll_No) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		Roll_No = roll_No;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getRoll_No() {
		return Roll_No;
	}
	public void setRoll_No(String roll_No) {
		Roll_No = roll_No;
	}
	
}
