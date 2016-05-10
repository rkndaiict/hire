package com.service.usermanagement.domain;

/**
 * 
 * @author h.v
 *
 */
public enum Status {

	PENDING_ACTIVATION("PENDING_ACTIVATION"),
	ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED"),
    LOCKED("LOCKED");
     
    private String state;
     
    private Status(final String state){
        this.state = state;
    }
     
    public String getState(){
        return this.state;
    }
 
    @Override
    public String toString(){
        return this.state;
    }
 
    public String getName(){
        return this.name();
    }
}
