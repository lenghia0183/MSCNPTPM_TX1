package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor

public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> SaveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable String code){
        OrganizationDto retrievedorganizationDto = organizationService.getOrganization(code);
        return  ResponseEntity.ok(retrievedorganizationDto);
    }
}
