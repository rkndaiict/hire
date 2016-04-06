package com.hire.company.vo;

import java.io.Serializable;

import com.common.base.vo.PaginationVO;

public class CompanySearchCriteriaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean loadActiveCompanies;

	private boolean loadInActiveCompanies;

	private boolean loadAllCompanies;

	private PaginationVO pagination;
	
	public boolean isLoadActiveCompanies() {
		return loadActiveCompanies;
	}

	public void setLoadActiveCompanies(boolean loadActiveCompanies) {
		this.loadActiveCompanies = loadActiveCompanies;
	}

	public boolean isLoadInActiveCompanies() {
		return loadInActiveCompanies;
	}

	public void setLoadInActiveCompanies(boolean loadInActiveCompanies) {
		this.loadInActiveCompanies = loadInActiveCompanies;
	}

	public boolean isLoadAllCompanies() {
		return loadAllCompanies;
	}

	public void setLoadAllCompanies(boolean loadAllCompanies) {
		this.loadAllCompanies = loadAllCompanies;
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
		builder.append("CompanySearchCriteriaVO [loadActiveCompanies=");
		builder.append(loadActiveCompanies);
		builder.append(", loadInActiveCompanies=");
		builder.append(loadInActiveCompanies);
		builder.append(", loadAllCompanies=");
		builder.append(loadAllCompanies);
		builder.append(", pagination=");
		builder.append(pagination);
		builder.append("]");
		return builder.toString();
	}
}
