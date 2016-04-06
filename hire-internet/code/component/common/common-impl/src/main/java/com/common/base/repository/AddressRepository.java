package com.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.base.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
