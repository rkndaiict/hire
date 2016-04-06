package com.hire.jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.hire.jobseeker.domain.JobProfileData;

@Repository
public interface JobProfileDataRepository extends
		JpaRepository<JobProfileData, Long> {

	@Query("select jpd from JobSeeker js inner join js.jobProfileData jpd WHERE "
			+ "js.jobSeekerIdentifier =: jobSeekerIdentifier")
	public JobProfileData findByJobSeekerIdentifier(
			@PathVariable("jobSeekerId") String jobSeekerIdentifier);

	@Query("select jpd from JobSeeker js inner join js.jobProfileData jpd WHERE js.userID =: userID")
	public JobProfileData findByJobSeekerUserId(
			@PathVariable("userID") String userID);

}
