package com.example.demo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

	private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
/*	We are annotating this method with @RabbitListener annotation this will read or consume message from the particular queue 
 consume method is subscribe the queue with the help of this annotation for that we need to pass that queue 
 (queues={"${rabbitmq.queue.name}"})*/
	
	@RabbitListener(queues={"${rabbitmq.queue.name}"})
	public void consume(String message) {
		LOGGER.info(String.format("Received message -> %s", message));
	}
}
