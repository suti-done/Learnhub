package com.Learnhub.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Learnhub.entity.user;

public interface UserRepository extends JpaRepository<user, Long> {
	
	Optional<user> findUserByUsername(String u);

	
}
