package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.hire.common.module.constant.NotifyEvent;
import com.hire.common.module.event.OnRegistrationCompleteEvent;
import com.hire.converter.util.UserConverter;
import com.hire.dto.UserDTO;
import com.hire.service.api.UserRegistrationService;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.Status;
import com.service.usermanagement.domain.UserData;
import com.service.usermanagement.domain.UserPassword;
import com.service.usermanagement.domain.UserProfileType;

public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserDataService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@Override
	public UserDTO registerNewUserAccount(final UserDTO accountDto) {
		if(emailExist(accountDto.getEmail())) {
			throw new RuntimeException("User account already exist");
		}
		
		UserData user = new UserData();
		user.setUserName(accountDto.getUserName());
		user.setStatus(Status.ACTIVE);
		UserProfileType userProfile = UserProfileType.valueOf(accountDto.getUserProfile());
		user.setUserProfile(userProfile);
		user.setUserIdentifier(accountDto.getEmail());
		
		UserPassword userPassword = new UserPassword();
		userPassword.setPassword(accountDto.getPassword());
		
		user.setUserPassword(userPassword);
		
		UserData userFromDB = userService.createUserData(user);
		
		//raise registration event
		 eventPublisher.publishEvent(new OnRegistrationCompleteEvent(NotifyEvent.SEEKER_REGISTRATION, null));
		
		return userConverter.mapUser(userFromDB);
	}

	@Override
	public void deleteUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean emailExist(final String email) {
		if(userService.findByUserIdentifier(email) != null) {
			return true;
		}
		return false;
	}

}
