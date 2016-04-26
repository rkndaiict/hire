package com.hire.service.api;

import com.hire.dto.UserDTO;

public interface UserServiceIntegration {

	public UserDTO findByUserName(String userId);
}
