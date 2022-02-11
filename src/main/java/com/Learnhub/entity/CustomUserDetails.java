package com.Learnhub.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	 private final user user;
	 
	 public CustomUserDetails(user user) {
	 this.user = user;
	 }
	 // Omitted code
	 public final user getUser() {
	 return user;
	 }
	
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
		 return user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList()) ;
		 
		
		}
	 
		@Override
		public String getPassword() {
		 return user.getPassword();
		}
		
		@Override
		public String getUsername() {
		 return user.getUsername();
		}
		@Override
		public boolean isAccountNonExpired() {
		 return true;
		}
		@Override
		public boolean isAccountNonLocked() {
		 return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
		 return true;
		}
		@Override
		public boolean isEnabled() {
		 return true;
		}


}
