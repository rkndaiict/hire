package com.hire.jobseeker.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hire.jobseeker.api.JobProfileDataService;
import com.hire.jobseeker.api.JobSeekerService;
import com.hire.jobseeker.domain.JobProfileData;
import com.hire.jobseeker.domain.JobSeeker;
import com.hire.jobseeker.repository.JobProfileDataRepository;

@Service
public class JobProfileDataServiceImpl implements JobProfileDataService {

	private static Logger logger = LoggerFactory
			.getLogger(JobProfileDataServiceImpl.class);

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private JobProfileDataRepository jobProfileDataRepository;

	@Override
	@Transactional(readOnly = false)
	public void createUpdateJobProfileData(String jobSeekerUserID,
			JobProfileData jobProfileData) {

		logger.debug("createJobProfileData : " + jobSeekerUserID);
		JobSeeker jobSeekerFromDB = jobSeekerService
				.getJobSeekerWithException(jobSeekerUserID);

		if (jobProfileData.getResume() == null
				&& jobSeekerFromDB.getJobProfileData() != null) {
			jobProfileData.setResume(jobSeekerFromDB.getJobProfileData()
					.getResume());
		}
		jobProfileData.setJobSeeker(jobSeekerFromDB);
		jobProfileDataRepository.save(jobProfileData);
	}

}
