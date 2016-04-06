package com.hire.company.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.base.constants.ErrorCode;
import com.common.base.constants.StatusEnum;
import com.common.base.exception.Error;
import com.common.base.exception.ServiceException;
import com.common.base.utils.mapper.ObjectMapper;
import com.hire.company.api.JobSeekerCompanyService;
import com.hire.company.domain.JobSeekerCompanyMapping;
import com.hire.company.repository.JobSeekerCompanyMappingRepository;

@Service
public class JobSeekerCompanyServiceImpl implements JobSeekerCompanyService {

	private static Logger logger = LoggerFactory
			.getLogger(JobSeekerCompanyServiceImpl.class);

	@Autowired
	private JobSeekerCompanyMappingRepository jobSeekerCompanyMappingRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@Transactional(readOnly = false)
	public void createUpdateJobSeekerForCompanies(
			JobSeekerCompanyMapping jobSeekerCompanyMapping)
			throws ServiceException {
		if (jobSeekerCompanyMapping == null) {
			logger.debug("Since jobSeekerCompanyMapping is null so returning w/o updating");
		}
		if (StringUtils.isBlank(jobSeekerCompanyMapping
				.getJobSeekerIdentifier())
				|| StringUtils.isBlank(jobSeekerCompanyMapping
						.getCompanyIdentifier())) {
			logger.debug(
					"Since In JobSeekerCompanyMapping JobSeekerIdentifier: {} OR CompanyIdentifier: {} is null so returning w/o updating:",
					jobSeekerCompanyMapping.getJobSeekerIdentifier(),
					jobSeekerCompanyMapping.getCompanyIdentifier());
		}

		JobSeekerCompanyMapping jobSeekerCompanyMappingFromDB = jobSeekerCompanyMappingRepository
				.findByJobSeekerIdentiferAndCompanyIdentifier(
						jobSeekerCompanyMapping.getJobSeekerIdentifier(),
						jobSeekerCompanyMapping.getCompanyIdentifier());

		if (jobSeekerCompanyMappingFromDB == null) {
			if (jobSeekerCompanyMapping.getStatus() == null) {
				jobSeekerCompanyMapping.setStatus(StatusEnum.ACTIVE);
			}

			jobSeekerCompanyMappingRepository.save(jobSeekerCompanyMapping);
		} else {
			jobSeekerCompanyMappingFromDB = objectMapper.copyProperties(
					jobSeekerCompanyMapping, jobSeekerCompanyMappingFromDB);
			jobSeekerCompanyMappingRepository
					.save(jobSeekerCompanyMappingFromDB);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void staleJobSeekerForCompany(String jobSeekerCompanyMappingIdentifer)
			throws ServiceException {
		JobSeekerCompanyMapping jobSeekerCompanyMappingFromDB = getJobSeekerCompanyMappingWithException(jobSeekerCompanyMappingIdentifer);
		jobSeekerCompanyMappingFromDB.setStatus(StatusEnum.ACTIVE);
		jobSeekerCompanyMappingRepository.save(jobSeekerCompanyMappingFromDB);
	}

	@Override
	@Transactional(readOnly = false)
	public void activateJobSeekerForCompany(
			String jobSeekerCompanyMappingIdentifer) throws ServiceException {
		JobSeekerCompanyMapping jobSeekerCompanyMappingFromDB = getJobSeekerCompanyMappingWithException(jobSeekerCompanyMappingIdentifer);
		jobSeekerCompanyMappingFromDB.setStatus(StatusEnum.STALE);
		jobSeekerCompanyMappingRepository.save(jobSeekerCompanyMappingFromDB);
	}

	@Override
	public JobSeekerCompanyMapping getJobSeekerCompanyMappingWithException(
			String jobSeekerCompanyMappingIdentifer) {
		JobSeekerCompanyMapping jobSeekerCompanyMappingFromDB = jobSeekerCompanyMappingRepository
				.findByIdentifier(jobSeekerCompanyMappingIdentifer);
		if (jobSeekerCompanyMappingFromDB == null) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"jobSeekerCompanyMappingFromDB is not found for identifier:"
							+ jobSeekerCompanyMappingIdentifer));
		}

		return jobSeekerCompanyMappingFromDB;
	}

	@Override
	public JobSeekerCompanyMapping getJobSeekerCompanyMapping(
			String jobSeekerCompanyMappingIdentifer) {
		JobSeekerCompanyMapping jobSeekerCompanyMappingFromDB = jobSeekerCompanyMappingRepository
				.findByIdentifier(jobSeekerCompanyMappingIdentifer);
		return jobSeekerCompanyMappingFromDB;
	}

}
