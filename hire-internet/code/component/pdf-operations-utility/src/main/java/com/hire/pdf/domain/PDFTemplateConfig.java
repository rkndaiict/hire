package com.hire.pdf.domain;

public class PDFTemplateConfig {

	private String context;

	private String primaryTemplateLocation;

	private String secondaryTemplateLocation;

	private String tableName;

	private int primaryTemplateRows;

	private int secondaryTemplateRows;

	private int tableStartIndex;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getPrimaryTemplateLocation() {
		return primaryTemplateLocation;
	}

	public void setPrimaryTemplateLocation(String primaryTemplateLocation) {
		this.primaryTemplateLocation = primaryTemplateLocation;
	}

	public String getSecondaryTemplateLocation() {
		return secondaryTemplateLocation;
	}

	public void setSecondaryTemplateLocation(String secondaryTemplateLocation) {
		this.secondaryTemplateLocation = secondaryTemplateLocation;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getPrimaryTemplateRows() {
		return primaryTemplateRows;
	}

	public void setPrimaryTemplateRows(int primaryTemplateRows) {
		this.primaryTemplateRows = primaryTemplateRows;
	}

	public int getSecondaryTemplateRows() {
		return secondaryTemplateRows;
	}

	public void setSecondaryTemplateRows(int secondaryTemplateRows) {
		this.secondaryTemplateRows = secondaryTemplateRows;
	}

	public int getTableStartIndex() {
		return tableStartIndex;
	}

	public void setTableStartIndex(int tableStartIndex) {
		this.tableStartIndex = tableStartIndex;
	}
}
