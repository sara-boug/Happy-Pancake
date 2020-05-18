package com.example.App.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.domain.RegistrationForm;
import com.example.App.domain.User;
import com.example.App.repository.JdbcUser;

@RestController
@RequestMapping(  produces="application/json" )
@CrossOrigin(origins="*")
public class UserRegistration {
	private JdbcUser jdbcUser; 
	private PasswordEncoder passwordEncoder ; 
	
	@Autowired
	public UserRegistration(JdbcUser jdbcUser , PasswordEncoder passwordEncoder) { 
		this.jdbcUser =jdbcUser; 
		this.passwordEncoder=passwordEncoder; 
	}
	
	@PostMapping( path="/signup" , consumes= "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User signupUser(@RequestBody RegistrationForm form) { 
		User  user = form.toUser(passwordEncoder); 	
		return  jdbcUser.save(user); 
	}
    
	@GetMapping(path="/login" , consumes= "application/json")
	public void loginUser( ) { 
		
		
	}
	
}
