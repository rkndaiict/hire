package com.hire.ns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hire.ns.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryCustom {
	
}