package com.hire.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.common.base.constants.RegistrationLinkStatus;
import com.common.base.domain.LinkMetaData;
import com.common.base.utils.DateUtil;
import com.hire.common.base.api.LinkMetadataService;
import com.hire.common.module.constant.EmailConstants;
import com.hire.common.module.constant.EventConstants;
import com.hire.common.module.constant.NotifyEvent;
import com.hire.common.module.event.GenerateNewRegistrationTokenEvent;
import com.hire.common.module.event.OnRegistrationCompleteEvent;
import com.hire.converter.util.UserConverter;
import com.hire.dto.UserDTO;
import com.hire.service.api.UserRegistrationServiceIntegration;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.Status;
import com.service.usermanagement.domain.UserData;
import com.service.usermanagement.domain.UserPassword;
import com.service.usermanagement.domain.UserProfileType;

public class UserRegistrationServiceImpl implements UserRegistrationServiceIntegration {

	@Autowired
	private UserDataService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private LinkMetadataService linkMetadataService;
	
	@Override
	public UserDTO registerNewUserAccount(final UserDTO accountDto) {
		if(emailExist(accountDto.getEmail())) {
			throw new RuntimeException("User account already exist");
		}
		
		UserData user = new UserData();
		user.setUserName(accountDto.getUserName());
		user.setUserIdentifier(UUID.randomUUID().toString());
		user.setStatus(Status.PENDING_ACTIVATION);
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
		if(userService.findByUserUserEmail(email) != null) {
			return true;
		}
		return false;
	}

	@Override
	public RegistrationLinkStatus verifyToken(String token) {
		
		LinkMetaData linkMetadata = linkMetadataService.getLinkMetadataByToken(token);
		
		if(linkMetadata == null) {
			return null;
		}
		//check link validity
		if(DateUtil.isFutureDate(linkMetadata.getTokenExpires()) || Boolean.TRUE.equals(linkMetadata.isActive())) {
			return RegistrationLinkStatus.ACTIVE;
		}
		
		return RegistrationLinkStatus.EXPIRED;
	}
	
	@Override
	public boolean activateUser(String userId) {
		UserData userFromDB = userService.findByUserIdentifier(userId);
		
		if(userFromDB != null) {
			userFromDB.setStatus(Status.ACTIVE);
			userService.updateUserData(userFromDB);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void generateNewToken(UserDTO userDTO) {
		
		LinkMetaData newLink = linkMetadataService.generateNewToken(userDTO.getUserIdentifier(), NotifyEvent.SEEKER_REGISTRATION);
		//raise a new event
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(EventConstants.TOKEN, newLink.getTokenIdentifier());
		paramMap.put(EventConstants.USER_IDENTIFIER, userDTO.getUserIdentifier());
		
		eventPublisher.publishEvent(new GenerateNewRegistrationTokenEvent(NotifyEvent.SEEKER_REGISTRATION, paramMap));
	}

}
