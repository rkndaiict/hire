package com.hire.jobseeker.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "Job_Profile_Data")
public class JobProfileData extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "is_Looking_For_Change")
	private boolean isLookingForChange;

	@Column(name = "resume")
	private Byte[] resume;

	@JoinColumn(name = "JOB_SEEKER_ID")
	private JobSeeker jobSeeker;

	public boolean isLookingForChange() {
		return isLookingForChange;
	}

	public void setLookingForChange(boolean isLookingForChange) {
		this.isLookingForChange = isLookingForChange;
	}

	public Byte[] getResume() {
		return resume;
	}

	public void setResume(Byte[] resume) {
		this.resume = resume;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobProfileData [isLookingForChange=");
		builder.append(isLookingForChange);
		builder.append(", resume=");
		builder.append(Arrays.toString(resume));
		builder.append(", jobSeeker=");
		builder.append(jobSeeker);
		builder.append("]");
		return builder.toString();
	}

}
