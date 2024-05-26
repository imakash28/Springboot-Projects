package com.example.demo.rabbitmq.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rabbitmq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

	//Here we will inject rabbitMQ producer so we will use constructor based dependency injection. we don't use @Autowired annotation for dependency injection because this spring bean has only one constructor i.e. parameterized constructor so we can omit to use @Autowired annotation
	private RabbitMQProducer producer;

	public MessageController(RabbitMQProducer producer) {
		this.producer = producer;
	}
	
//	Lets create a Rest API

//	We are going to pass the message in the URL by using Query Parameter so URL will look like this
	// (http://localhost:8080/api/v1/publish?message=hello) to get this message from the url we will use RequestParam in parameter
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		producer.sendMessage(message);
		return ResponseEntity.ok("Message sent to RabbitMQ...");
	}
	
}
