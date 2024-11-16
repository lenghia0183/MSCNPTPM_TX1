package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope //this annotation is responsible to reflect a change in the application.properties file without restarting a particular microservice.
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("message")
    public String message(){
        return message;
    }
}
