package com.hire.agency.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.base.exception.ServiceException;
import com.hire.agency.api.CompanySearchService;
import com.hire.company.domain.Company;
import com.hire.company.vo.CompanySearchCriteriaVO;

@Service
public class CompanySearchServiceImpl implements CompanySearchService {

	private static Logger logger = LoggerFactory
			.getLogger(CompanySearchServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Company> getAllCompanies(
			CompanySearchCriteriaVO companySearchCriteriaVO)
			throws ServiceException {
		logger.debug("Inside CompanySearchService : " + companySearchCriteriaVO);
		
		Criteria criteria = ((Session)entityManager.getDelegate()).createCriteria(Company.class);
		
		if(companySearchCriteriaVO != null){
			if(companySearchCriteriaVO.isLoadActiveCompanies()){
				criteria.add(Restrictions.eq("status", "ACTIVE"));
			}
			else if(companySearchCriteriaVO.isLoadInActiveCompanies()){
				criteria.add(Restrictions.eq("status", "INACTIVE"));
			}
		}
		
		return criteria.list();
	}

}
