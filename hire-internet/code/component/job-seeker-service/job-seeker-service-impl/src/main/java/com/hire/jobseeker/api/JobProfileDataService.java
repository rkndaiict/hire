package com.hire.jobseeker.api;

import com.common.base.exception.ServiceException;
import com.hire.jobseeker.domain.JobProfileData;

public interface JobProfileDataService {

	public void createUpdateJobProfileData(String jobSeekerUserID,
			JobProfileData JobProfileData) throws ServiceException;

}
