package com.smart.service;

import com.smart.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDto> getAll();

    OrganizationDto getById(Long id);

    List<OrganizationDto> getByParentId(Long orgId);
}
