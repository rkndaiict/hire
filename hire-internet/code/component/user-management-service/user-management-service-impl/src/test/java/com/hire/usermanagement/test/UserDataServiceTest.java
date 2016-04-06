package com.hire.usermanagement.test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.usermanagement.api.UserDataService;
import com.service.usermanagement.domain.UserData;
import com.service.usermanagement.domain.UserOrganization;
import com.service.usermanagement.domain.UserPassword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/testApplicationContext.xml" })
public class UserDataServiceTest {

	private static Logger Logger = LoggerFactory
			.getLogger(UserDataServiceTest.class);

	@Autowired
	private UserDataService userDataService;
	
	@Test
	public void testAddUserData() {
		UserData userData = prepareUserData("rahul");
		userDataService.createUserData(userData);

	}

	@Test
	public void testAddUserDataWithOrganization() {
		UserData userData = prepareUserData("rahultwo");
		UserOrganization userOrganization = userDataService.createUserOrganization("INDIVIDUAL");
		userDataService.createUserData(userData);
		userDataService.attachUserOrganizationWithUserData(userData, userOrganization);
	}

	@Test
	public void testAddUserPassword() {
		UserData userData = userDataService.getUserData("rahul");
		userData.setUserPassword(new UserPassword());
		userData.getUserPassword().setPassword("password");
		userDataService.createPassword("rahul",userData.getUserPassword());

	}

	private UserData prepareUserData(String userName) {
		UserData userData = new UserData();
		
		userData.setUserName(userName);
		return userData;
	}
}
