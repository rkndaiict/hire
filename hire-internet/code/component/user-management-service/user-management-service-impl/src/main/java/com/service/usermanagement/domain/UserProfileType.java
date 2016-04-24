package com.service.usermanagement.domain;

public enum UserProfileType {

	AGENCY("AGENCY"),
    SEEKER("SEEKER"),
    ADMIN("ADMIN");
     
    String userProfileType;
     
    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
     
    public String getUserProfileType(){
        return userProfileType;
    }
}
