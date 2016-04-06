package com.hire.jobseeker.vo;

import java.io.Serializable;

import com.common.base.vo.PaginationVO;

public class JobSeekerSearchCriteriaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean loadActiveJobSeekers;

	private boolean loadInActiveJobSeekers;

	private boolean loadAllJobSeekers;

	private boolean loadAllJobSeekersWithResume;

	private PaginationVO pagination;

	public boolean isLoadActiveJobSeekers() {
		return loadActiveJobSeekers;
	}

	public void setLoadActiveJobSeekers(boolean loadActiveJobSeekers) {
		this.loadActiveJobSeekers = loadActiveJobSeekers;
	}

	public boolean isLoadInActiveJobSeekers() {
		return loadInActiveJobSeekers;
	}

	public void setLoadInActiveJobSeekers(boolean loadInActiveJobSeekers) {
		this.loadInActiveJobSeekers = loadInActiveJobSeekers;
	}

	public boolean isLoadAllJobSeekers() {
		return loadAllJobSeekers;
	}

	public void setLoadAllJobSeekers(boolean loadAllJobSeekers) {
		this.loadAllJobSeekers = loadAllJobSeekers;
	}

	public boolean isLoadAllJobSeekersWithResume() {
		return loadAllJobSeekersWithResume;
	}

	public void setLoadAllJobSeekersWithResume(
			boolean loadAllJobSeekersWithResume) {
		this.loadAllJobSeekersWithResume = loadAllJobSeekersWithResume;
	}

	public PaginationVO getPagination() {
		return pagination;
	}

	public void setPagination(PaginationVO pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobSeekerSearchCriteriaVO [loadActiveJobSeekers=");
		builder.append(loadActiveJobSeekers);
		builder.append(", loadInActiveJobSeekers=");
		builder.append(loadInActiveJobSeekers);
		builder.append(", loanAllJobSeekers=");
		builder.append(loadAllJobSeekers);
		builder.append(", pagination=");
		builder.append(pagination);
		builder.append("]");
		return builder.toString();
	}
}
