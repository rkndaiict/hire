package com.hire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.dto.UserDTO;
import com.hire.service.api.UserServiceIntegration;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.UserData;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
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
		
		mapperFactory.classMap(UserData.class, UserDTO.class)
			.field("userIdentifier", "userIdentifier")
			.field("userName", "userName")
			.field("userPassword.password", "password")
			.field("status", "status")
			.field("userProfile", "userProfile")
			.customize(new CustomMapper<UserData, UserDTO>() {
				@Override
			    public void mapAtoB(UserData source, UserDTO target, MappingContext context) {
			      // some mapping logic here...
			    }

			    @Override
			    public void mapBtoA( UserDTO source, UserData target, MappingContext c) {
			      // mapping logic here...
			   }
			})
			.register();
		
		UserDTO userDTO = mapperFactory.getMapperFacade().map(user, UserDTO.class);
		return userDTO;
	}


}
