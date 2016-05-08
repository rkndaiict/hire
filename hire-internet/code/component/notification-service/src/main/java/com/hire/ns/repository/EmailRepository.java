package com.hire.ns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hire.ns.domain.Email;


public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryCustom {
	
}