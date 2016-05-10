package com.hire.service.api;


import com.common.base.constants.RegistrationLinkStatus;
import com.hire.dto.UserDTO;

public interface UserRegistrationServiceIntegration {
	
	UserDTO registerNewUserAccount(UserDTO accountDto);
	
	RegistrationLinkStatus verifyToken(String token);
	
	void deleteUser(UserDTO user);

	boolean activateUser(String userId);

	void generateNewToken(UserDTO userDTO);

}
