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
import com.hire.agency.api.AgencySearchService;
import com.hire.company.domain.Company;
import com.hire.jobseeker.domain.JobSeeker;
import com.hire.jobseeker.vo.JobSeekerSearchCriteriaVO;

@Service
public class AgencySearchServiceImpl implements AgencySearchService {

	private static Logger logger = LoggerFactory
			.getLogger(AgencySearchServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<JobSeeker> getAllJobSeekers(
			JobSeekerSearchCriteriaVO jobSeekerSearchCriteriaVO)
			throws ServiceException {
		logger.debug("Inside CompanySearchService : "
				+ jobSeekerSearchCriteriaVO);

		Criteria criteria = ((Session) entityManager.getDelegate())
				.createCriteria(JobSeeker.class);

		if (jobSeekerSearchCriteriaVO != null) {
			if (jobSeekerSearchCriteriaVO.isLoadActiveJobSeekers()) {
				criteria.add(Restrictions.eq("status", "ACTIVE"));
			} else if (jobSeekerSearchCriteriaVO.isLoadInActiveJobSeekers()) {
				criteria.add(Restrictions.eq("status", "INACTIVE"));
			}
		}

		return criteria.list();
	}

}
