package com.hire.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hire.company.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("select c from Company c where c.companyName =:companyName")
	public Company findByCompanyName(@Param("companyName") String companyName);

	@Query("select c from Company c where c.userId =:userId")
	public Company findByUserId(@Param("userId") String userId);

}
