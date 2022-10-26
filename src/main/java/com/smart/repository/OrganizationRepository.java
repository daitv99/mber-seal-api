package com.smart.repository;

import com.smart.dto.OrganizationDto;
import com.smart.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<OrganizationDto> getByOrgParentId(Long orgId);

    List<OrganizationDto> getAllBySysOrganizationIdIsNotNull();

    OrganizationDto getBySysOrganizationId(Long orgId);
}
