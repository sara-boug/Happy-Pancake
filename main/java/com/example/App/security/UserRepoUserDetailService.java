package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.App.domain.User;
import com.example.App.repository.JdbcUser;
 

@Service
public class UserRepoUserDetailService implements UserDetailsService {
	private JdbcUser jdbcUser; 
	@Autowired
	public UserRepoUserDetailService (JdbcUser jdbcUser) { 
		this.jdbcUser= jdbcUser; 
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		User user  =jdbcUser.findByUserName(email); 	
		if(user!=null) { 
 			return user; 
		} else { 
 			throw new UsernameNotFoundException("Bad Credentials") ; 

		}
 	}

}
