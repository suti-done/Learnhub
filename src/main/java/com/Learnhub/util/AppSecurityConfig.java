package com.Learnhub.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;





@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	 private AuthenticationProviderService authenticationProvider;
	
	
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
		//jdbc authentication
    	//auth.jdbcAuthentication().dataSource(securityDataSource);
    	 //auth.userDetailsService(userService);
    	auth.authenticationProvider(authenticationProvider);
    	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home").authenticated()
		.antMatchers("/tutor/**").hasRole("TUTOR")
		.antMatchers("/").access("permitAll")
		.antMatchers("/register").access("permitAll")
		.antMatchers("/saveUser").access("permitAll")
		.and()
        .formLogin().defaultSuccessUrl("/home")
        .permitAll()
        .and()
		.logout().permitAll();
		
		super.configure(http);
	    http.csrf().disable();
		//.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	

}
