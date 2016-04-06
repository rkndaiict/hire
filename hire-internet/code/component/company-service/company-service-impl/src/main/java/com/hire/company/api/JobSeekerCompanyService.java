package com.hire.company.api;

import com.common.base.exception.ServiceException;
import com.hire.company.domain.JobSeekerCompanyMapping;

public interface JobSeekerCompanyService {

	public void createUpdateJobSeekerForCompanies(
			JobSeekerCompanyMapping jobSeekerCompanyMapping)
			throws ServiceException;

	public JobSeekerCompanyMapping getJobSeekerCompanyMapping(
			String jobSeekerCompanyMappingIdentifer) throws ServiceException;

	public JobSeekerCompanyMapping getJobSeekerCompanyMappingWithException(
			String jobSeekerCompanyMappingIdentifer) throws ServiceException;

	public void staleJobSeekerForCompany(String jobSeekerCompanyMappingIdentifer)
			throws ServiceException;

	public void activateJobSeekerForCompany(
			String jobSeekerCompanyMappingIdentifer) throws ServiceException;
}
