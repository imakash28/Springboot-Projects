package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaService;

@RestController
@RequestMapping("/location")
public class DeliveryAppController {

	@Autowired
	private KafkaService kafkaService;
	
	@PostMapping("/update")
	public ResponseEntity<?> updateLocation(){
		for(int i=1;i<=100000;i++) {
//		this.kafkaService.updateLocation("("+Math.random()*100+" , "+Math.random()*100+")");	//3 digit location
		this.kafkaService.updateLocation("("+Math.round(Math.random()*100)+" , "+Math.round(Math.random()*100)+")"); //Round fig 3 digit location
		}
		return new ResponseEntity<>(Map.of("message","Location updated."),HttpStatus.OK);
	}
}
