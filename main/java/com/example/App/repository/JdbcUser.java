package com.example.App.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.App.domain.User;
import com.example.App.repository_interface.UserRepo;

@Repository 
public class JdbcUser implements UserRepo {
	JdbcTemplate template; 
    public JdbcUser(JdbcTemplate template) { 
    	this.template= template; 
    	
    }
	@Override
	public User findByUserName(String fullname) {
		return  template.queryForObject("select * from users where fullname =? ", this::mapRowUserDetail, fullname); 
	}
		
	private User mapRowUserDetail(ResultSet rs , int rowNum) throws SQLException { 
		return new User(
				rs.getString("fullname"),
 				rs.getString("email"),
 				rs.getString("phoneNumber"),
 				rs.getString("password")
				);
		
	}
	@Override
	public User save(User user) {
		template.update("insert into users fullname, email ,phonenumbre , password values(?,?,?,?) ", 
				user.getUsername(),
				user.getemail(),
				user.getphoneNumber(),
				user.getPassword()
				); 
		return user; 
 	}
	
	

}
