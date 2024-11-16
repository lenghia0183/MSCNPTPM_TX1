package com.example.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Department Service Rest APIs",
				description = "Department Service Rest API Documentation",
				version = "v1.0",
				contact = @Contact(
						name =" Srinath Sai Tripuraneni",
						email = "stripur@okstate.edu",
						url = "https://github.com/srinathsai"
				),
				license = @License(                                         // for editing rest api documentation information.
						name = "Apache 2.0",
						url = "https://github.com/srinathsai"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Department- Service Doc",
				url = "https://github.com/srinathsai"
		)
)



@SpringBootApplication

public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
