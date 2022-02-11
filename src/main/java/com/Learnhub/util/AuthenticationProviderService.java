package com.Learnhub.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Learnhub.dao.JpaUserDetailsService;
import com.Learnhub.entity.CustomUserDetails;
@Service
public class AuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 String username = authentication.getName();
		 String password = authentication
		 .getCredentials()
		 .toString();
		 CustomUserDetails u = userDetailsService.loadUserByUsername(username);
		 if (passwordEncoder.matches(password, u.getPassword())) {
			 return new UsernamePasswordAuthenticationToken(
			 username, 
			 password, 
			 u.getAuthorities()); 
			 } else {
			 throw new BadCredentialsException
			 ("Something went wrong!"); 
			 }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return true; 
	}

}
