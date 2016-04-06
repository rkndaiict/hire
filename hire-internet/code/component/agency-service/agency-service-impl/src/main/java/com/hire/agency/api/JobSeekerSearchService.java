package com.hire.agency.api;

import java.util.List;

import com.common.base.exception.ServiceException;
import com.hire.jobseeker.domain.JobSeeker;
import com.hire.jobseeker.vo.JobSeekerSearchCriteriaVO;

public interface JobSeekerSearchService {

	public List<JobSeeker> getAllJobSeekers(
			JobSeekerSearchCriteriaVO jobSeekerSearchCriteriaVO)
			throws ServiceException;
	
}
