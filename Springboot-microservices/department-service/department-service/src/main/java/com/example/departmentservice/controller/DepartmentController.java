package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Department Service - DepartmentController",
        description = "Department Controller Exposes REST APIs for Department-Service"
)
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @Operation(
            summary =  "Save Department REST API ",
            description = "Save Department REST API is used to save department object in a database"
    )
    @ApiResponse(                                                                                     // these annotations used to give summary and description of rest apis
            responseCode = "201",                                                                     // in rest api documentation.
            description = "HTTP Status 201 Created"
    )

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto= departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @Operation(
            summary =  "Get Department REST API ",
            description = "Get Department REST API is used to save department object from database"
    )
    @ApiResponse(                                                                                     // these annotations used to give summary and description of rest apis
            responseCode = "200",                                                                     // in rest api documentation.
            description = "HTTP Status 200 Success"
    )

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
       DepartmentDto retrievedDepartmentDto= departmentService.retrieveDepartment(departmentCode);
       return new ResponseEntity<>(retrievedDepartmentDto,HttpStatus.OK);

    }



}
