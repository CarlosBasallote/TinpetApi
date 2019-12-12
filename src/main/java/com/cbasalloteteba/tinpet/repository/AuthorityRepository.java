package com.cbasalloteteba.tinpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbasalloteteba.tinpet.model.Authority;
import com.cbasalloteteba.tinpet.model.AuthorityName;



public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	Authority findFirstByName(AuthorityName name);


}
