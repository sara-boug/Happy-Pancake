package com.example.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.App.domain.RegistrationForm;
import com.example.App.domain.User;
import com.example.App.repository.JdbcUser;

@Controller
@RequestMapping("/user")
public class UserRegistration {
	private JdbcUser jdbcUser; 
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	public UserRegistration(JdbcUser jdbcUser ,PasswordEncoder passwordEncoder ) { 
		this.jdbcUser= jdbcUser;
		this.passwordEncoder=passwordEncoder;
		
	}
	
	@GetMapping("/register")
	public String register() { 
		return "register"; 
	}
	@PostMapping
   public String processRegistration(RegistrationForm form) { 
		this.jdbcUser.save(form.toUser(passwordEncoder)); 
	
	   return "redirect:/login"; 
   }
}
