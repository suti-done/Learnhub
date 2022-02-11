package com.Learnhub.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Learnhub.entity.Authority;
import com.Learnhub.entity.user;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	

	
}
