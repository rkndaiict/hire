package com.service.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.usermanagement.domain.UserOrganization;

@Repository
public interface UserOrganizationRepository extends
		JpaRepository<UserOrganization, Long> {

}
