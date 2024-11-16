package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    public OrganizationDto saveOrganization(OrganizationDto organizationDto);

    public OrganizationDto getOrganization(String code);
}
