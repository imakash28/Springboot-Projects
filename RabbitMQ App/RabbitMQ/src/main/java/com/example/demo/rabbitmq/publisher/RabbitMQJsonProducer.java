package com.example.demo.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.rabbitmq.dto.User;

@Service
public class RabbitMQJsonProducer {
// In order to pass the JSON message we need a JSON and routing queue
//	Once we got the exchange and routing json key we can able to send JSON message to the Queue
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
/*	In-order to send a JSON message to queue we need a rabbitTemplate so lets go and inject rabbitTemplate here
	We will use constructor based dependency injection to inject rabbitTemplate */
	private RabbitTemplate rabbitTemplate; 
	
	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
//	Now we will create a method which whill send JSON message to the Queue
/*	we will pass a User object as parameter and send it using rabbitTemplate and rabbitTemplate internally uses message converter 
	to convert this user object into JSON*/
	
	public void sendJsonMessage(User user) {
		LOGGER.info(String.format("Json message sent -> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
	}
	
}
