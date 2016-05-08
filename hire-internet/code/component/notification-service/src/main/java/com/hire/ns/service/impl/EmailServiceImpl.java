package com.hire.ns.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hire.ns.constants.EmailConstants;
import com.hire.ns.domain.Email;
import com.hire.ns.domain.MessageTemplate;
import com.hire.ns.dto.EmailDTO;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.exception.MailServiceException;
import com.hire.ns.repository.EmailRepository;
import com.hire.ns.service.api.EmailService;
import com.hire.ns.util.EmailUtil;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	@Transactional(readOnly = false)
	public void sendEmail(EmailDTO emailDTO) {
		LOGGER.debug("Sending email to :" + emailDTO.getToEmail());

		Email nsEmail = emailRepository.findOne(emailDTO.getEmailId());

		if (nsEmail.getMessageTemplate() != null) {
			LOGGER.info(
					"As per Message Template , the SENDABLE flag is set to {}. Hence, sending out email with id {}.",
					nsEmail.getMessageTemplate().isSendable(), emailDTO.getEmailId());
		} else {
			LOGGER.info("Cannot find the associated message template. Hence, sending out email with id {}. ", emailDTO.getEmailId());
		}


		try {
			emailUtil.sendEmail(nsEmail);

			LOGGER.debug("Email send successfully to:" + nsEmail.getToEmail());

		} catch (MailServiceException mailServiceException) {
			LOGGER.error(mailServiceException.getMessage(), mailServiceException);
			
		}

		LOGGER.info("Finished sending email to :" + nsEmail.getToEmail());
	}

	private List<EmailDTO> convert(List<Email> nsEmails) {
		List<EmailDTO> emailDTOs = new ArrayList<EmailDTO>();
		for (Email nsEmail : nsEmails) {
			EmailDTO emailDTO = new EmailDTO();
			emailDTO.setEmailId(nsEmail.getId());
			emailDTO.setToEmail(nsEmail.getToEmail());
			emailDTOs.add(emailDTO);
		}
		return emailDTOs;
	}

	@Override
	public Email saveUpdateEmailNotification(NotificationDTO notificationDTO, Map<String, Object> params,
			List<byte[]> files, MessageTemplate messageTemplate) {
		
		LOGGER.debug("Saving email for :" + params.get(EmailConstants.MAIL_TO_ADDRESS));
		Email email = createEmail(notificationDTO, params, messageTemplate);

		emailRepository.save(email);
		
		LOGGER.debug("Mail successfully saved for :" + email.getToEmail());
		return email;
	}
	
	private Email createEmail(NotificationDTO notificationDTO, Map<String, Object> params, MessageTemplate messageTemplate) {

		Email email = new Email();
		email.setBccEmail((String) params.get(EmailConstants.MAIL_BCC_ADDRESS));
		email.setToEmail((String) params.get(EmailConstants.MAIL_TO_ADDRESS));
		email.setSubject((String) params.get(EmailConstants.MAIL_SUBJECT));
		email.setCcEmail((String) params.get(EmailConstants.MAIL_CC_ADDRESS));
		email.setFromEmail((String) params.get(EmailConstants.MAIL_FROM_ADDRESS));
		email.setMessageTemplate(messageTemplate);

		if (null != notificationDTO.getEmailBody()) {
			email.setBody(notificationDTO.getEmailBody().getBytes());
		}

		return email;
	}
}
