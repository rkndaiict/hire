package com.hire.ns.repository;

import java.util.List;

import com.hire.ns.domain.EmailRecipientType;
import com.hire.ns.domain.MessageTemplate;

public interface MessageTemplateRepositoryCustom {

    MessageTemplate findByEventAndRecipientType(String event, EmailRecipientType eventRecipientType);

	public List<MessageTemplate> findAllMessageTemplate();

	public List<MessageTemplate> getEmailTemplateByRecipientType(EmailRecipientType eventRecipientType);

}
