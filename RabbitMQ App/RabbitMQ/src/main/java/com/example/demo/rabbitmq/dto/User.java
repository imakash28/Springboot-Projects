package com.example.demo.rabbitmq.dto;

import lombok.Data;

// This is our User POJO class for serialize/Deserialize 
@Data
public class User {

	private int id;
	private String firstName;
	private String lastName;
	
}
