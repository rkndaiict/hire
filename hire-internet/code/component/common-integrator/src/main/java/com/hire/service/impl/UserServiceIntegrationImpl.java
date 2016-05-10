package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.converter.util.UserConverter;
import com.hire.dto.UserDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.UserData;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration{

	@Autowired
	private UserDataService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDTO findByUserName(String userName) {
		
		UserData userFromDB = userService.getUserData(userName);
		return userConverter.mapUser(userFromDB);
	}


}
