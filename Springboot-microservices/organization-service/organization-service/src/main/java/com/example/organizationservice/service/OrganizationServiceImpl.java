package com.example.organizationservice.service;

import com.example.organizationservice.Mapper.OrganizationMapper;
import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.exception.ResourceNotFoundException;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization SavedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(SavedOrganization);

    }

    @Override
    public OrganizationDto getOrganization(String code) {
        Organization retrievedOrganization = organizationRepository.findByOrganizationCode(code);
        if(retrievedOrganization==null) {
            throw new ResourceNotFoundException("Organization with mentioned code is not present in db");
        }
        return OrganizationMapper.mapToOrganizationDto(retrievedOrganization);
    }
}


