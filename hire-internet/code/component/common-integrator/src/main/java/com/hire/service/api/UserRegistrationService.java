package com.hire.service.api;


import com.hire.dto.UserDTO;

public interface UserRegistrationService {
	
	UserDTO registerNewUserAccount(UserDTO accountDto);
	
	void deleteUser(UserDTO user);

}
