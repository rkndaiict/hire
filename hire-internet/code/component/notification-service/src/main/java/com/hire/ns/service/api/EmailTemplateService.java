package com.hire.ns.service.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hire.ns.dto.MessageTemplateDTO;

public interface EmailTemplateService {

	List<MessageTemplateDTO> getAllEmailTemplate() throws UnsupportedEncodingException;

	MessageTemplateDTO updateEmailTemplateContent(MessageTemplateDTO emailTemplateDTO)
			throws UnsupportedEncodingException;

	MessageTemplateDTO getEmailTemplateById(Long templateId) throws UnsupportedEncodingException;

}
