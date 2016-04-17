package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.dto.UserDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermgmt.api.UserService;
import com.service.usermgmt.domain.User;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration{

	@Autowired
	private UserService userService;
	
	@Autowired
    MapperFacade mapperFacade;
	
	@Override
	public UserDTO findByUserId(String userId) {
		
		User userFromDB = userService.getUserData(userId);
		return mapUser(userFromDB);
	}
	
	private UserDTO mapUser(User user) {
		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(User.class, UserDTO.class)
		   .byDefault()
		   .register();
		
		return mapperFacade.map(user, UserDTO.class);
	}


}
