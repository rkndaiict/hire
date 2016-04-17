package com.service.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.usermgmt.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(@Param("userName") String userName);
}
