package com.example.demo.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service	//To make this class as spring bean and it will register in a spring IOC container
public class RabbitMQProducer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
//	Here, In this class  we will write the logic to send the message to the rabbitMQ broker
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	// We will use rabbitTemplate to send the messages. Springboot automatically configure RabbitTemplate for us. we just need to inject and use it.
	
	private RabbitTemplate rabbitTemplate;

//	@Autowired  Spring automatically autowired this
	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
//	Within the sendMessage method we need to put a log statement to print the message
	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s", message));
//		Use the rabbit template method to send the message to the particular queue
/*		Reason to select convertAndSend(String exchnage, String routingKey, Object object) because exchange will basically use this routing key to route 
		this particular object/message to the queue*/
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
}
}