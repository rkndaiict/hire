package com.hire.ns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hire.ns.domain.MessageTemplate;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

    
}
