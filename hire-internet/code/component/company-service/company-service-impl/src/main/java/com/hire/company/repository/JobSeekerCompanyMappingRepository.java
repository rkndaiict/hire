package com.hire.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hire.company.domain.JobSeekerCompanyMapping;

@Repository
public interface JobSeekerCompanyMappingRepository extends
		JpaRepository<JobSeekerCompanyMapping, Long> {

	@Query("select jsc from JobSeekerCompany jsc where jsc.identifier=:identifier")
	public JobSeekerCompanyMapping findByIdentifier(
			@Param("identifier") String identifier);

	@Query("select jsc from JobSeekerCompany jsc where jsc.jobSeekerIdentifer=:jobSeekerIdentifer")
	public List<JobSeekerCompanyMapping> findByJobSeekerIdentifer(
			@Param("jobSeekerIdentifer") String jobSeekerIdentifer);

	@Query("select jsc from JobSeekerCompany jsc where jsc.jobSeekerIdentifer=:jobSeekerIdentifer and jsc.companyIdentifier=:companyIdentifier")
	public JobSeekerCompanyMapping findByJobSeekerIdentiferAndCompanyIdentifier(
			@Param("jobSeekerIdentifer") String jobSeekerIdentifer,
			@Param("companyIdentifier") String companyIdentifier);

}
