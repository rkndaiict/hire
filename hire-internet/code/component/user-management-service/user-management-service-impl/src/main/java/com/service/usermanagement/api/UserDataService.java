package com.service.usermanagement.api;

import com.common.base.exception.ServiceException;
import com.service.usermanagement.domain.MasterOrganization;
import com.service.usermanagement.domain.UserData;
import com.service.usermanagement.domain.UserOrganization;
import com.service.usermanagement.domain.UserPassword;

public interface UserDataService {

	public void createUserData(UserData userData) throws ServiceException;

	public UserData updateUserData(UserData userData) throws ServiceException;

	public UserOrganization createUserOrganization(
			UserOrganization userOrganization) throws ServiceException;

	public UserOrganization createUserOrganization(String organizationName)
			throws ServiceException;

	public void createPassword(String userName, UserPassword userPassword)
			throws ServiceException;

	public UserData getUserData(String userName) throws ServiceException;

	public UserData getUserDataWithException(String userName)
			throws ServiceException;

	public UserPassword getUserPassword(String userName)
			throws ServiceException;

	public MasterOrganization getMasterOrganization(
			String masterOrganizationName) throws ServiceException;

	public UserOrganization getUserOrganization(String userName,
			String organizationName) throws ServiceException;

	public void attachUserOrganizationWithUserData(UserData userData,
			UserOrganization userOrganization) throws ServiceException;

}
