package com.hire.jobseeker.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constants.ErrorCode;
import com.common.base.exception.Error;
import com.common.base.exception.ServiceException;
import com.common.base.utils.mapper.ObjectMapper;
import com.hire.jobseeker.api.JobProfileDataService;
import com.hire.jobseeker.api.JobSeekerService;
import com.hire.jobseeker.domain.JobSeeker;
import com.hire.jobseeker.repository.JobSeekerRepository;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

	private static Logger logger = LoggerFactory
			.getLogger(JobSeekerServiceImpl.class);

	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JobProfileDataService jobProfileDataService;

	@Override
	public JobSeeker createJobSeeker(JobSeeker jobSeeker) throws ServiceException {

		logger.debug("CreateJobSeeker:" + jobSeeker.getUserID());
		JobSeeker jobSeekerFromDB = getJobSeeker(jobSeeker.getUserID());
		if (jobSeekerFromDB != null) {
			return updateJobSeeker(jobSeekerFromDB);
		}
		else{
			jobSeekerRepository.save(jobSeeker);
			return jobSeeker;
		}
	}

	@Override
	public JobSeeker updateJobSeeker(JobSeeker jobSeeker) throws ServiceException {
		JobSeeker jobSeekerFromDB = getJobSeekerWithException(jobSeeker
				.getUserID());
		jobSeekerFromDB = objectMapper.copyProperties(jobSeeker,
				jobSeekerFromDB);
		if (jobSeeker.getJobProfileData() != null) {
			jobProfileDataService.createUpdateJobProfileData(
					jobSeeker.getUserID(), jobSeeker.getJobProfileData());
		}
		jobSeekerRepository.save(jobSeekerFromDB);
		return jobSeekerFromDB;
	}

	@Override
	public JobSeeker getJobSeeker(String userId) throws ServiceException {
		JobSeeker jobSeeker = jobSeekerRepository.findByUserId(userId);
		return jobSeeker;
	}

	@Override
	public JobSeeker getJobSeekerByIdentifier(String jobSeekerIdentifier)
			throws ServiceException {
		return jobSeekerRepository
				.findByJobSeekerIdentifier(jobSeekerIdentifier);

	}

	@Override
	public JobSeeker getJobSeekerWithException(String userId)
			throws ServiceException {
		JobSeeker jobSeeker = jobSeekerRepository.findByUserId(userId);
		if (jobSeeker == null) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"JobSeeker not found with userId:" + userId));
		}
		return jobSeeker;
	}
}
