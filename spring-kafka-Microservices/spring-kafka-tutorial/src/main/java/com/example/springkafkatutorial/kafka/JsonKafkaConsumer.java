package com.example.springkafkatutorial.kafka;

import com.example.springkafkatutorial.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="example_json",groupId = "myGroup") //A kafka annotation used to consume for particular topic and groupId from applications.properties.
    public void consume(User user){
        LOGGER.info(String.format("Message received -> %s",user.toString()));
    }
}
