package com.hire.agency.api;

import java.util.List;

import com.common.base.exception.ServiceException;
import com.hire.company.domain.Company;
import com.hire.company.vo.CompanySearchCriteriaVO;

public interface CompanySearchService {

	public List<Company> getAllCompanies(
			CompanySearchCriteriaVO companySearchCriteriaVO)
			throws ServiceException;


}
