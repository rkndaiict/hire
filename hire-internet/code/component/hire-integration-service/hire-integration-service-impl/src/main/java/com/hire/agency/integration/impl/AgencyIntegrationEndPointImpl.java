package com.hire.agency.integration.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constants.ErrorCode;
import com.common.base.exception.Error;
import com.common.base.exception.ServiceException;
import com.common.base.utils.mapper.ObjectMapper;
import com.hire.agency.api.AgencyService;
import com.hire.agency.domain.Agency;
import com.hire.agency.integration.api.AgencyIntegrationEndPoint;
import com.hire.agency.integration.domain.AgencyDTO;

@Service
public class AgencyIntegrationEndPointImpl implements AgencyIntegrationEndPoint {

	private static Logger logger = LoggerFactory
			.getLogger(AgencyIntegrationEndPointImpl.class);

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public AgencyDTO createAgency(AgencyDTO agencyDTO) throws ServiceException {
		if (agencyDTO == null) {
			String errorMessage = "AgencyDTO is null";
			logger.error(errorMessage);
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					errorMessage));
		}

		Agency agency = objectMapper.copyProperties(agencyDTO, Agency.class);
		agency = agencyService.createUpdateAgency(agency);
		return objectMapper.copyProperties(agency, AgencyDTO.class);
	}

	@Override
	public AgencyDTO updateAgency(AgencyDTO agencyDTO) throws ServiceException {
		if (agencyDTO == null) {
			String errorMessage = "AgencyDTO is null";
			logger.error(errorMessage);
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					errorMessage));
		}

		Agency agency = objectMapper.copyProperties(agencyDTO, Agency.class);
		agency = agencyService.createUpdateAgency(agency);
		return objectMapper.copyProperties(agency, AgencyDTO.class);
	}

	@Override
	public AgencyDTO getAgency(String agencyIdentifier) throws ServiceException {

		Agency agency = agencyService.getAgency(agencyIdentifier);
		if (agency == null) {
			return null;
		}

		return objectMapper.copyProperties(agency, AgencyDTO.class);
	}

	@Override
	public AgencyDTO getAgencyByUserID(String userID) throws ServiceException {
		Agency agency = agencyService.getAgencyByUserId(userID);
		if (agency == null) {
			return null;
		}

		return objectMapper.copyProperties(agency, AgencyDTO.class);
	}

}
