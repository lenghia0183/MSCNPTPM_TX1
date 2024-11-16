package com.example.springkafkatutorial.controller;

import com.example.springkafkatutorial.kafka.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/kafka")
public class MessageController {
    private KafkaProducer kafkaProducer;
    public MessageController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;    // Injecting dependency injection because here in service layer this kafkaProducer object will use kafkaTemplate a key,value pair to store the query value from url to a particular kafka topic.
    }

   //http:/localhost:8080/api/vi/kafka/publish?message=hello world
    @GetMapping("/publish")
    public ResponseEntity<String>publish(@RequestParam("message") String message){
            kafkaProducer.sendMessage(message);
            return new ResponseEntity<>("Message sent to created topic", HttpStatus.OK);
    }
}
