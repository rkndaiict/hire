package com.service.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.usermanagement.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

	public UserData findByUserName(@Param("userName") String userName);

	public UserData findByUserIdentifier(@Param("userIdentifier") String userIdentifier);
	
	public UserData findByUserEmail(@Param("email") String email);
}
