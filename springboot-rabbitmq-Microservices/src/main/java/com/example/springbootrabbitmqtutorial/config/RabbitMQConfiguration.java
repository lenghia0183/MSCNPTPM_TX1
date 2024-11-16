package com.example.springbootrabbitmqtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.queue.name}")
    private String queue;             //from applications.properties file this value is taken to reduce hardcoding.

    @Value("${rabbitmq.exchange.name}")
    private String exchange;         // from application.properties file this value is taken to reduce hardcoding.

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Bean
    public Queue queue(){
        return new Queue(queue);    //configuring queue with a name.
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange); // configuring exchange with a name.
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey); // we are using binding builder to bind queue returned by queue() to exchange returned by exchange() with specified routing key.
    }

    //ConnectionFactory and RabbitTemplate, RabbitAdmin are the 3 spring beans that are configured automatically by Springboot Auto configuration.
}
