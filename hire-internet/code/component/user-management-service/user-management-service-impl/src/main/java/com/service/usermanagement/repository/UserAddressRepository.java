package com.service.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.usermanagement.domain.PersonAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<PersonAddress, Long> {

}
