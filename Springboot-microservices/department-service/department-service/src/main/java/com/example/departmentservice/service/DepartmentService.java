package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    public DepartmentDto saveDepartment(DepartmentDto departmentDto);

    public DepartmentDto retrieveDepartment(String departmentCode);
}
