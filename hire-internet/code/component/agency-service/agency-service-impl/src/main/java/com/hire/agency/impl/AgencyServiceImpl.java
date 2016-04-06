package com.hire.agency.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.exception.ServiceException;
import com.common.base.utils.mapper.ObjectMapper;
import com.hire.agency.api.AgencyService;
import com.hire.agency.domain.Agency;
import com.hire.agency.repository.AgencyRepository;

@Service
public class AgencyServiceImpl implements AgencyService {

	private static Logger logger = LoggerFactory
			.getLogger(AgencyServiceImpl.class);

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Agency createUpdateAgency(Agency agency) throws ServiceException {
		if (agency == null) {
			logger.debug("Since agency is null so returning w/o updating");
			return null;
		}

		if (StringUtils.isBlank(agency.getAgencyIdentifier())) {
			agency = agencyRepository.saveAndFlush(agency);
		} else {
			Agency agencyFromDB = agencyRepository
					.findAgencyByAgencyIdentifier(agency.getAgencyIdentifier());
			if (agencyFromDB == null) {
				agency = agencyRepository.save(agency);
			} else {
				agencyFromDB = objectMapper
						.copyProperties(agency, agencyFromDB);
				agency = agencyRepository.save(agencyFromDB);
			}
		}
		return agency;
	}

	@Override
	public Agency getAgency(String agencyIdentifier) throws ServiceException {
		return agencyRepository.findAgencyByAgencyIdentifier(agencyIdentifier);
	}

	@Override
	public Agency getAgencyByUserId(String userIdentifier)
			throws ServiceException {
		return agencyRepository.findAgencyByUserID(userIdentifier);
	}
}
