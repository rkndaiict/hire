package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.dto.UserDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.UserData;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration{

	@Autowired
	private UserDataService userService;
	
	@Autowired
    MapperFacade mapperFacade;
	
	@Override
	public UserDTO findByUserId(String userId) {
		
		UserData userFromDB = userService.getUserData(userId);
		return mapUser(userFromDB);
	}
	
	private UserDTO mapUser(UserData user) {
		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserData.class, UserDTO.class)
		   .byDefault()
		   .register();
		
		return mapperFacade.map(user, UserDTO.class);
	}


}
