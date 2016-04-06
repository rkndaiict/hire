package com.hire.agency.integration.api;

import com.common.base.exception.ServiceException;
import com.hire.agency.integration.domain.AgencyDTO;

public interface AgencyIntegrationEndPoint {

	public AgencyDTO createAgency(AgencyDTO agencyDTO) throws ServiceException;
	
	public AgencyDTO updateAgency(AgencyDTO agencyDTO) throws ServiceException;
	
	public AgencyDTO getAgency(String agencyIdentifier) throws ServiceException;
	
	public AgencyDTO getAgencyByUserID(String userID) throws ServiceException;
}
