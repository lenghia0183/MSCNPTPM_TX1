package com.example.springkafkatutorial.kafka;

import com.example.springkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private KafkaTemplate<String, User> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data){

        LOGGER.info(String.format("Message sent -> %s",data.toString()));

        //this code is creating a Kafka message with a specified payload (of type User) and setting a header to specify the Kafka topic to which the message should be sent
        Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,"example_json").build();

        kafkaTemplate.send(message);

    }

}
