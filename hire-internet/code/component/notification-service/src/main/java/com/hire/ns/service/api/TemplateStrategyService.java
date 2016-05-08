package com.hire.ns.service.api;

import com.hire.ns.dto.NotificationDTO;


public interface TemplateStrategyService {
	
	/**
	 * Send email with database template.
	 *
	 * @param emailDTO the email dto
	 * @param authorization the authorization
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 */
	void sendEmailWithDatabaseTemplate(NotificationDTO emailDTO) throws Exception;
	
}
