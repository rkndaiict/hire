package com.service.usermgmt.api;

import com.common.base.exception.ServiceException;
import com.service.usermgmt.domain.User;

/**
 * 
 * @author h.v
 *
 */
public interface UserService {

	public User getUserData(String userName) throws ServiceException;
}
