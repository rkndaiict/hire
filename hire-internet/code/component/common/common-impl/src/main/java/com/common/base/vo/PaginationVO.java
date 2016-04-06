package com.common.base.vo;

import java.io.Serializable;

public class PaginationVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer startIndex;

	private Integer pageSize;

	private String sortColumn;

	private String sortOrder;

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaginationVO [startIndex=");
		builder.append(startIndex);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", sortColumn=");
		builder.append(sortColumn);
		builder.append(", sortOrder=");
		builder.append(sortOrder);
		builder.append("]");
		return builder.toString();
	}

}
