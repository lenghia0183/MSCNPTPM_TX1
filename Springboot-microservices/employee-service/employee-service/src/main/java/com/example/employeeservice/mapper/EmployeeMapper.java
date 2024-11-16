package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDepartmentCode(employee.getDepartmentCode());
        employeeDto.setOrganizationCode(employee.getOrganizationCode());
        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        employee.setOrganizationCode(employeeDto.getOrganizationCode());
        return employee;
    }
}
