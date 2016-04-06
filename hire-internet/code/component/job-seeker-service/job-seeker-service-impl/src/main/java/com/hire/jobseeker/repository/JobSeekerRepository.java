package com.hire.jobseeker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.hire.jobseeker.domain.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	@Query("select * from JobSeeker js WHERE js.jobSeekerIdentifier =: jobSeekerIdentifier")
	public JobSeeker findByJobSeekerIdentifier(
			@PathVariable("jobSeekerIdentifier") String jobSeekerIdentifier);

	@Query("select * from JobSeeker js WHERE js.userId =: userId")
	public JobSeeker findByUserId(@PathVariable("userId") String userId);

	@Query("select * from JobSeeker js inner join js.jobProfileData jobProfileData WHERE jobProfileData.isLookingForChange = true ")
	public List<JobSeeker> findAllActiveJobSeeker();
}
