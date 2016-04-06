package com.hire.agency.api;

import com.common.base.exception.ServiceException;
import com.hire.agency.domain.Agency;

public interface AgencyService {

	public Agency createUpdateAgency(Agency agency) throws ServiceException;

	public Agency getAgency(String agencyIdentifier) throws ServiceException;

	public Agency getAgencyByUserId(String userIdentifier)
			throws ServiceException;
}
