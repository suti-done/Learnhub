package com.Learnhub.dao;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Learnhub.entity.CustomUserDetails;
import com.Learnhub.entity.user;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	 @Autowired
	 private UserRepository userRepository;
	 

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 @Override
	 public CustomUserDetails loadUserByUsername(String username) {
		 
		 Supplier<UsernameNotFoundException> s = 
		 () -> new UsernameNotFoundException(
		 "Problem during authentication!");
		 user u = userRepository
		 .findUserByUsername(username) 
		 .orElseThrow(s); 
	 
	     return new CustomUserDetails(u); 
	 }

     @Transactional
	 public void addUser(user user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userRepository.save(user);
	 }
     
     @Transactional
     public user getUser(String username) {
		 
    	 Supplier<UsernameNotFoundException> s = 
    			 () -> new UsernameNotFoundException(
    			 "Problem during authentication!");
		 user u = userRepository
		 .findUserByUsername(username) 
		 .orElseThrow(s); 
	 
	     return u; 
	 }
}
