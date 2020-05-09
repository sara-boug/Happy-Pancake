package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.App.domain.User;
import com.example.App.repository.JdbcUser;
 

public class UserRepoUserDetailSrevice implements UserDetailsService {
	private JdbcUser jdbcUser; 
	@Autowired
	public UserRepoUserDetailSrevice (JdbcUser jdbcUser) { 
		this.jdbcUser= jdbcUser; 
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user  =jdbcUser.findByUserName(username); 
		if(user!=null) { 
			return user; 
		}
		throw new UsernameNotFoundException("User "+ username + "Not Found") ; 
 	}

}