package com.hire.ns.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hire.ns.domain.MessageTemplate;
import com.hire.ns.dto.MessageTemplateDTO;
import com.hire.ns.repository.MessageTemplateRepository;
import com.hire.ns.repository.MessageTemplateRepositoryCustom;
import com.hire.ns.service.api.EmailTemplateService;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private MessageTemplateRepositoryCustom messageTemplateRepositoryCustom;
	
	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	
	@Override
	public List<MessageTemplateDTO> getAllEmailTemplate() throws UnsupportedEncodingException {
		List<MessageTemplate> allMessageTemplate = messageTemplateRepositoryCustom.findAllMessageTemplate();
		return convertMessagetemplate(allMessageTemplate);
	}
	
	@Override
	@Transactional(readOnly = false)
	public MessageTemplateDTO updateEmailTemplateContent(
			MessageTemplateDTO emailTemplateDTO) throws UnsupportedEncodingException {
		MessageTemplate messagetemplate = messageTemplateRepository.findOne(emailTemplateDTO.getMessageTemplateId());
		String emailContent = "Subject:" + emailTemplateDTO.getSubject() + "\n" + emailTemplateDTO.getEmailContent();
		messagetemplate.setTemplate(emailContent.getBytes("UTF-8"));
		messagetemplate = messageTemplateRepository.save(messagetemplate);
		return convertMessagetemplate(messagetemplate);
	}
	
	@Override
	public MessageTemplateDTO getEmailTemplateById(Long templateId) throws UnsupportedEncodingException {
		MessageTemplate messagetemplate = messageTemplateRepository.findOne(templateId);
		return convertMessagetemplate(messagetemplate);
	}

	private List<MessageTemplateDTO> convertMessagetemplate(List<MessageTemplate> messagetemplateList) throws UnsupportedEncodingException{
		List<MessageTemplateDTO> emailTemplateDTOList = new ArrayList<MessageTemplateDTO>();
		
		for(MessageTemplate messagetemplate : messagetemplateList){
			MessageTemplateDTO emailTemplateDTO = convertMessagetemplate(messagetemplate);
			emailTemplateDTOList.add(emailTemplateDTO);
		}
		
		return emailTemplateDTOList;
	}
	
	private MessageTemplateDTO convertMessagetemplate(MessageTemplate messagetemplate) throws UnsupportedEncodingException{
		if(messagetemplate == null){
			return null;
		}
		MessageTemplateDTO emailTemplateDTO = new MessageTemplateDTO();
		emailTemplateDTO.setMessageTemplateId(messagetemplate.getId());
		emailTemplateDTO.setEvent(messagetemplate.getEvent());
		emailTemplateDTO.setSendable(Boolean.TRUE.equals(messagetemplate.isSendable()));
		
		String emailContent =  new String(messagetemplate.getTemplate(), "UTF-8");
		emailTemplateDTO.setEmailContent(emailContent.substring(emailContent.indexOf("\n")));
		emailTemplateDTO.setSubject(emailContent.substring(8, emailContent.indexOf("\n")));
		return emailTemplateDTO;
	}

}
