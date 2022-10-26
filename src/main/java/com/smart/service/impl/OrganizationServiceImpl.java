package com.smart.service.impl;

import com.smart.dto.OrganizationDto;
import com.smart.repository.OrganizationRepository;
import com.smart.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<OrganizationDto> getAll() {
        return repository.getAllBySysOrganizationIdIsNotNull();
    }

    @Override
    public OrganizationDto getById(Long id) {
        return repository.getBySysOrganizationId(id);
    }

    @Override
    public List<OrganizationDto> getByParentId(Long orgId) {
        return repository.getByOrgParentId(orgId);
    }
}
