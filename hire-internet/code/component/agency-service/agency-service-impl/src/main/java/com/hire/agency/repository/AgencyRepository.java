package com.hire.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.hire.agency.domain.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

	@Query("select * from Agency a where a.userID =: userID")
	public Agency findAgencyByUserID(@PathVariable("userID") String userID);

	@Query("select * from Agency a where a.agencyIdentifier =: agencyIdentifier")
	public Agency findAgencyByAgencyIdentifier(
			@PathVariable("agencyIdentifier") String agencyIdentifier);

}
