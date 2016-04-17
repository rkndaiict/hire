package com.service.usermgmt.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.base.exception.ServiceException;
import com.service.usermgmt.api.UserService;
import com.service.usermgmt.domain.User;
import com.service.usermgmt.repository.UserRepository;

/**
 * 
 * @author h.v
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserData(String userName) throws ServiceException {
		if (StringUtils.isBlank(userName)) {
			return null;
		}

		User userDataFromDB = userRepository.findByUserName(userName);

		return userDataFromDB;
	}

}
