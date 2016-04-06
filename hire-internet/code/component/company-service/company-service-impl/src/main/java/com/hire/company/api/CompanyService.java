package com.hire.company.api;

import com.common.base.exception.ServiceException;
import com.hire.company.domain.Company;

public interface CompanyService {

	public Company createCompany(Company company) throws ServiceException;

	public Company updateCompany(Company company) throws ServiceException;

	public Company getCompany(String userId) throws ServiceException;

	public Company getCompanyByName(String companyName) throws ServiceException;

	public Company getCompanyByNameWithException(String companyName)
			throws ServiceException;

}
