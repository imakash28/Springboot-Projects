package com.example.demo.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue; /*we using this because we are using spring AMQP model to work with rabbitMQ 
messaging system*/
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
/*we need to pass queue name we need to make it configurable that means we don't need to hardcode this queue name in our code.
 *  we need to externalized this queue name to lets define it into the app.properties file and use @Value annotation to read this value 
	from the app.properties file then we will pass this value to this object */
/*	Here we use @Value annotation to read this value from the app.properties file then we will pass this value i.e. queue 
	to this Queue class constructor*/
	
	@Value("${rabbitmq.queue.name}")	//Here we are making it queue name as configurable
	private String queue;
	
	@Value("${rabbitmq.queue.json.name}")	//Here we are making it queue name as configurable
	private String jsonQueue;
	
	
	@Value("${rabbitmq.exchange.name}")	//Here we are making it exchange name as configurable
	private String exchange;
	
	
	@Value("${rabbitmq.routing.key}")	//Here we are making it routing key as configurable
	private String routingKey;
	
	
	@Value("${rabbitmq.routing.json.key}")	//Here we are making it routing key as configurable
	private String routingJsonKey;
	
	
//	spring bean for rabbitmq queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
		//return new Queue("javaguides");  

	}
	
	
	
//	spring bean for rabbitmq exchnage
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
		//return new TopicExchange("javaguides_exchange");

	}
	
	
	
//	Here we have bind this queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(exchange())
				.with(routingKey);
				//.with("javaguides_routing_key");
	}
	
	
/*Apart from above 3 beans we need to configure some more couple of beans which are Infrastructure bean 
 * that are required our springboot application to work with rabbitMQ broker but springboot
 * autoconfiguration automatically configure those 3 beans for us we don't need to explitly create these 3 beans for these class*/
	
	
//	Bean for ConnectionFactory
//	Bean for RabbitTemplate
//	Bean for RabbitAdmin
	
//	Spring bean for queue( to store JSON messages)
@Bean	
public Queue jsonQueue() {
	return new Queue(jsonQueue);
}

// we need to  bind this jsonQueue with exchange using routing key
@Bean
public Binding jsonBinding() {
	return BindingBuilder
			.bind(jsonQueue())
			.to(exchange())
			.with(routingJsonKey);
	}

// This is the message converter RabbitTemplate method will use it internally to convert Java object into JSON and JSON to Java object.
@Bean
public MessageConverter converter() {
	return new Jackson2JsonMessageConverter();
}
// Now we are going to configure rabbitTemplate and then we set this converter to the rabbitTemplate
@Bean
public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
	RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
	rabbitTemplate.setMessageConverter(converter());
	return rabbitTemplate;
}
}
