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
import com.hire.agency.api.JobSeekerSearchService;
import com.hire.jobseeker.domain.JobSeeker;
import com.hire.jobseeker.vo.JobSeekerSearchCriteriaVO;

@Service
public class JobSeekerSearchServiceImpl implements JobSeekerSearchService {

	private static Logger logger = LoggerFactory
			.getLogger(JobSeekerSearchServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<JobSeeker> getAllJobSeekers(
			JobSeekerSearchCriteriaVO jobSeekerSearchCriteriaVO)
			throws ServiceException {
		logger.debug("Inside JobSeekerSearchServiceImpl : "
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

		if (jobSeekerSearchCriteriaVO != null) {
			if(jobSeekerSearchCriteriaVO .isLoadAllJobSeekersWithResume()){
				criteria.createAlias("jobProfileData", "jobProfileData");
				criteria.add(Restrictions.isNotEmpty("jobProfileData.resume"));
			}
		}
		
		return criteria.list();
	}

}
