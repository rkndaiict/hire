package com.service.usermanagement.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.base.constants.ErrorCode;
import com.common.base.exception.Error;
import com.common.base.exception.ServiceException;
import com.common.base.utils.CommonUtil;
import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.MasterOrganization;
import com.service.usermanagement.domain.UserData;
import com.service.usermanagement.domain.UserOrganization;
import com.service.usermanagement.domain.UserPassword;
import com.service.usermanagement.repository.MasterOrganizationRepository;
import com.service.usermanagement.repository.UserDataRepository;
import com.service.usermanagement.repository.UserOrganizationRepository;
import com.service.usermanagement.repository.UserPasswordRepository;

@Service
public class UserDataServiceImpl implements UserDataService {

	public static Logger logger = LoggerFactory.getLogger(UserDataServiceImpl.class);

	@Autowired
	private UserDataRepository userDataRepository;

	@Autowired
	private UserPasswordRepository userPasswordRepository;

	@Autowired
	private MasterOrganizationRepository masterOrganizationRepository;

	@Autowired
	private UserOrganizationRepository userOrganizationRepository;
	
	@Override
	@Transactional(readOnly=false)
	public UserData createUserData(UserData userData) throws ServiceException {

		if (StringUtils.isBlank(userData.getUserName())) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"UserName is empty."));
		}

		UserData userDataFromDB = getUserData(userData.getUserName());

		if (userDataFromDB != null) {
			updateUserData(userData);
			return userData;
		}
		
		userDataRepository.save(userData);
		
		return userData;
	}

	@Override
	@Transactional(readOnly=false)
	public UserData updateUserData(UserData userData) throws ServiceException {
		if (StringUtils.isBlank(userData.getUserName())) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"UserName is empty."));
		}

		UserData userDataFromDB = getUserDataWithException(userData
				.getUserName());

		userDataRepository.save(userDataFromDB);
		return userDataFromDB;
	}

	@Override
	@Transactional(readOnly=false)
	public void createPassword(String userName, UserPassword userPassword)
			throws ServiceException {
		UserData userDataFromDB = getUserDataWithException(userName);

		userPassword.setUserData(userDataFromDB);
		userPasswordRepository.save(userPassword);
	}

	@Override
	public UserData getUserData(String userName) throws ServiceException {
		if (StringUtils.isBlank(userName)) {
			return null;
		}

		UserData userDataFromDB = userDataRepository.findByUserName(userName);

		return userDataFromDB;
	}

	@Override
	public UserData getUserDataWithException(String userName)
			throws ServiceException {
		if (StringUtils.isBlank(userName)) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"UserName is empty."));
		}

		UserData userDataFromDB = userDataRepository.findByUserName(userName);
		if (userDataFromDB == null) {
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA,
					"User doesn't exists with userName : " + userName));
		}

		return userDataFromDB;
	}

	@Override
	public UserPassword getUserPassword(String userName)
			throws ServiceException {
		UserData userData = getUserDataWithException(userName);
		return userData.getUserPassword();
	}

	@Override
	public MasterOrganization getMasterOrganization(
			String masterOrganizationName) throws ServiceException {
		MasterOrganization masterOrganization = masterOrganizationRepository.findByOrganizationName(masterOrganizationName);
		return masterOrganization;
	}

	@Override
	public UserOrganization getUserOrganization(String userName,
			String organizationName) throws ServiceException {

		UserData userDataFromDB = getUserDataWithException(userName);
		if (!CommonUtil.checkIfNullOrEmpty(userDataFromDB
				.getUserOrganizations())) {
			for (UserOrganization userOrganization : userDataFromDB
					.getUserOrganizations()) {
				if (userOrganization == null
						|| !StringUtils.equals(userOrganization
								.getMasterOrganization().getOrganizationName(),
								organizationName)) {
					continue;
				}

				return userOrganization;
			}
		}

		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public UserOrganization createUserOrganization(UserOrganization userOrganization)
			throws ServiceException {
		userOrganizationRepository.save(userOrganization);
		return userOrganization;
	}

	@Override
	@Transactional(readOnly=false)
	public UserOrganization createUserOrganization(String organizationName)
			throws ServiceException {
		MasterOrganization masterOrganization = masterOrganizationRepository.findByOrganizationName(organizationName);
		if(masterOrganization == null){
			throw new ServiceException(new Error(ErrorCode.INSUFFICIENT_DATA, "Master Organzation is null for :" + organizationName));
		}
		UserOrganization userOrganization = new UserOrganization();
		userOrganization.setMasterOrganization(masterOrganization);
		userOrganizationRepository.save(userOrganization);
		return userOrganization;
	}

	@Override
	public void attachUserOrganizationWithUserData(UserData userData,
			UserOrganization userOrganization) throws ServiceException {
		if(userData.getUserOrganizations() == null){
			userData.setUserOrganizations(new ArrayList<UserOrganization>());
		}
		
		userData.getUserOrganizations().add(userOrganization);
		userDataRepository.save(userData);
	}

	@Override
	public UserData findByUserIdentifier(String userIdentifier) {
		return userDataRepository.findByUserIdentifier(userIdentifier);
	}

}
