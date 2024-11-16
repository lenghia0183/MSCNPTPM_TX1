package com.example.organizationservice.Mapper;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setCreatedDate(organizationDto.getCreatedDate());

        return organization;

    }

    public static OrganizationDto mapToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setOrganizationCode(organization.getOrganizationCode());
        organizationDto.setOrganizationDescription(organization.getOrganizationDescription());
        organizationDto.setOrganizationName(organization.getOrganizationName());
        organizationDto.setCreatedDate(organization.getCreatedDate());

        return organizationDto;

    }
}
