package com.service.usermgmt.domain;

public enum Status {

	ACTIVE("Active"),
	INACTIVE("Inactive"),
	DELETED("Deleted"),
	LOCKED("Locked");
	
	private String status;
	
	private Status(final String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}

	@Override
	public String toString(){
		return this.status;
	}

	public String getName(){
		return this.name();
	}


}
