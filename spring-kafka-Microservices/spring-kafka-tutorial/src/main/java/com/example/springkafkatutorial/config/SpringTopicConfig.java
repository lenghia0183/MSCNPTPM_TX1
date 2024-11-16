package com.example.springkafkatutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class SpringTopicConfig {

    @Bean
    public NewTopic exampleTopic(){
        return TopicBuilder.name("example").build(); //internally springboot wil split this built kafka topic into default number of partitions. A name with example for a topic is created.
    }

    @Bean
    public NewTopic exampleTopicJson(){
        return TopicBuilder.name("example_json").build(); //internally springboot wil split this built kafka topic into default number of partitions. A name with example for a topic is created.
    }
}
