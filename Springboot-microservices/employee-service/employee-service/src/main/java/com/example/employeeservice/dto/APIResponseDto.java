package com.example.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
    private EmployeeDto employeeDto;          //This class is used to send in the combination of 2 or more classes implies this is the combined class
                                             // of 2 or more services of any rest API call between 2 microservices.
    private DepartmentDto departmentDto;

    private OrganizationDto organizationDto;
}
