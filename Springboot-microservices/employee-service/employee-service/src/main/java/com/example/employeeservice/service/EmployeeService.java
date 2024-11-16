package com.example.employeeservice.service;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public APIResponseDto getEmployee(Long id);
}
