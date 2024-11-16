package com.example.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "DepartmentDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @Schema(
            description = "Department Name"  // this annotation is used to describe dto model information in Spring REST API Documentation.
    )
    private String departmentName;
    @Schema(
            description = " Department Description"
    )
    private String departmentDescription;

    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
}
