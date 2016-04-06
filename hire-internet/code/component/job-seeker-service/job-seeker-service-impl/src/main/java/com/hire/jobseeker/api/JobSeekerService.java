package com.hire.jobseeker.api;

import com.common.base.exception.ServiceException;
import com.hire.jobseeker.domain.JobSeeker;

public interface JobSeekerService {

	public JobSeeker createJobSeeker(JobSeeker jobSeeker) throws ServiceException;

	public JobSeeker updateJobSeeker(JobSeeker jobSeeker) throws ServiceException;

	public JobSeeker getJobSeeker(String userId) throws ServiceException;

	public JobSeeker getJobSeekerWithException(String userId) throws ServiceException;

	public JobSeeker getJobSeekerByIdentifier(String jobSeekerIdentifier)
			throws ServiceException;


}
