package com.example.departmentservice.service;

import com.example.departmentservice.Mapper.DepartmentMapper;
import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DepartmenterviceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto retrieveDepartment(String departmentCode) {
        Department retrievedDepartment = departmentRepository.findByDepartmentCode(departmentCode);
        if(retrievedDepartment==null){
            throw new ResourceNotFoundException("There is no department with code" + departmentCode);
        }
        return DepartmentMapper.mapToDepartmentDto(retrievedDepartment);
    }
}
