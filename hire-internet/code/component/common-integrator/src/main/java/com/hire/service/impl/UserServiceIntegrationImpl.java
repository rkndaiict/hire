package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.dto.UserDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.UserData;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration{

	@Autowired
	private UserDataService userService;
	
	@Override
	public UserDTO findByUserName(String userName) {
		
		UserData userFromDB = userService.getUserData(userName);
		return mapUser(userFromDB);
	}
	
	private UserDTO mapUser(UserData user) {
		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		UserDTO userDTO = mapperFactory.getMapperFacade().map(user, UserDTO.class);
		
		return userDTO;
	}


}
