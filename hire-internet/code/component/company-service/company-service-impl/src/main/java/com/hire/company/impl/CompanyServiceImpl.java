package com.hire.company.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constants.ErrorCode;
import com.common.base.exception.Error;
import com.common.base.exception.ServiceException;
import com.common.base.utils.mapper.ObjectMapper;
import com.hire.company.api.CompanyService;
import com.hire.company.domain.Company;
import com.hire.company.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static Logger logger = LoggerFactory
			.getLogger(CompanyServiceImpl.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company createCompany(Company company) throws ServiceException {
		if (company == null) {
			logger.debug("Since company is null so returning w/o creating");
			return company;
		}
		if (StringUtils.isBlank(company.getCompanyName())) {
			logger.debug("Since company.getCompanyName() is null so returning w/o creating");
			return company;
		}

		Company companyFromDB = companyRepository.findByCompanyName(company
				.getCompanyName());
		if (companyFromDB == null) {
			companyRepository.save(company);
			return company;
		} else {
			return updateCompany(companyFromDB);
		}
	}

	@Override
	public Company updateCompany(Company company) throws ServiceException {
		if (company == null) {
			logger.debug("Since company is null so returning w/o creating");
			return company;
		}
		if (StringUtils.isBlank(company.getCompanyName())) {
			logger.debug("Since company.getCompanyName() is null so returning w/o creating");
			return company;
		}

		Company companyFromDB = getCompanyByNameWithException(company
				.getCompanyName());
		companyFromDB = objectMapper.copyProperties(company, companyFromDB);
		companyRepository.save(companyFromDB);
		return companyFromDB;
	}

	@Override
	public Company getCompany(String userId) throws ServiceException {
		Company companyFromDB = companyRepository.findByUserId(userId);
		return companyFromDB;
	}

	@Override
	public Company getCompanyByName(String companyName) throws ServiceException {
		Company companyFromDB = companyRepository
				.findByCompanyName(companyName);
		return companyFromDB;
	}

	@Override
	public Company getCompanyByNameWithException(String companyName)
			throws ServiceException {
		Company companyFromDB = companyRepository
				.findByCompanyName(companyName);
		if (companyFromDB == null) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"Company doesn't exist for companyName:" + companyName));
		}
		return companyFromDB;
	}

}
