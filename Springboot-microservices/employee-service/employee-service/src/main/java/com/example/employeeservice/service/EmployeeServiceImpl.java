package com.example.employeeservice.service;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment") // this annotation is responsible for roll back mechanism if department service is defected.
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployee(Long id) {
        LOGGER.info("inside getEmployee() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no employee with id" + id));

        EmployeeDto retrievedEmployeeDto=EmployeeMapper.mapToEmployeeDto(employee);

        /*ResponseEntity<DepartmentDto> responseEntity=restTemplate.getForEntity("http://localhost:8080/api/departments/" + retrievedEmployeeDto.getDepartmentCode(), DepartmentDto.class); //Due to dependency injection you will get a response entity class.
        DepartmentDto departmentDto = responseEntity.getBody(); //gets department dto class object from the body of responseEntity. */

        DepartmentDto departmentDto =webClient.get().uri("http://localhost:8080/api/departments/" + retrievedEmployeeDto.getDepartmentCode()).retrieve().bodyToMono(DepartmentDto.class).block();
        //(webclient object first uri is passed with other microservice field. It will be returning as Mono as it is from Spring webflux model) (we have used webClient to make a restAPI call to Departmentservice from employee service). */

        OrganizationDto organizationDto =webClient.get().uri("http://localhost:8083/api/organizations/" + retrievedEmployeeDto.getOrganizationCode()).retrieve().bodyToMono(OrganizationDto.class).block();
        //for making a rest Api call from employee microservice to organization microservice.

        //DepartmentDto departmentDto = apiClient.getDepartment(retrievedEmployeeDto.getDepartmentCode()); // for Spring openFeign client.
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(retrievedEmployeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto; //we are sending the combination of 2 classes which is a result of restApicalls between 2 microservices.
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception exception) {  //default department method specified in the parameter of circuitbreaker is defined with same signature.
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no employee with id" + id));

        EmployeeDto retrievedEmployeeDto=EmployeeMapper.mapToEmployeeDto(employee);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentName("R and D Department"); //this is the case when department service microservice is broken then a default department is created and sent.
        departmentDto.setDepartmentDescription("Research and Development Department");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(retrievedEmployeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto; //we are sending the combination of 2 classes which is a result of restApicalls between 2 microservices.
    }


}
