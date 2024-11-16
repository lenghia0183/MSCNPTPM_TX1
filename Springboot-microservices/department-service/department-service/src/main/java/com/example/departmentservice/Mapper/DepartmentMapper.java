package com.example.departmentservice.Mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;

public class DepartmentMapper {
    public static Department mapToDepartment(DepartmentDto departmentDto){
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        return department;
    }

    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setDepartmentCode(department.getDepartmentCode());
        departmentDto.setDepartmentName(department.getDepartmentName());
        departmentDto.setDepartmentDescription(department.getDepartmentDescription());
        return departmentDto;
    }
}
