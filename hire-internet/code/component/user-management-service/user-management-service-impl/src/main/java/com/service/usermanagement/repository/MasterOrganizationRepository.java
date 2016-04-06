package com.service.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.usermanagement.domain.MasterOrganization;

@Repository
public interface MasterOrganizationRepository extends
		JpaRepository<MasterOrganization, Long> {

	public MasterOrganization findByOrganizationName(String organizationName);

}
