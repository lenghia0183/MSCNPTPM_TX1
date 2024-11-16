package com.example.employeeservice.service;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DEPARTMENT-SERVICE") // this annotation will tell Spring open Feign to create dynamic implementation of this interface.
public interface APIClient {

    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode); // the api method which is called from this microservice should be placed here.
}

//We don't have to write the implementation of the interface because OpenFeign Client will create dynamically the implementation of this interface.
//just creating an interface annotating with OpenFeign client annotations, configuring base urls and the method to which rest api call is passed must be placed here.
//As we have uploaded spring cloud Netflix Eureka client server dependency only name
// of the microservice that a rest api call from this microservice is required.
//Spring load balancer inbuiltly will make this rest-api to respective instance which is available.