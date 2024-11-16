package com.example.employeeservice;

import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients // this annotation enables component scanning for interfaces that declare they are feign clients.
public class EmployeeServiceApplication {

	/*@Bean
	public RestTemplate restTemplate(){    // we use restTemplate spring bean for making a rest api call between 2 microservices.
		return new RestTemplate();
	}*/

	@Bean
	public WebClient webClient(){
		return  WebClient.builder().build(); //Configuring webClient type to bean type.
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
