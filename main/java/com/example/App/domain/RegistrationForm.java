package com.example.App.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {

	private String Username; 
	private String email ; 
	private String phoneNumber; 
	private String Password; 
  
	public User toUser(PasswordEncoder passwordEncoder) { 
		return new User(
				this.Username, this.email , this.phoneNumber, passwordEncoder.encode(this.Password)								); 
	}
	public String getemail() {
		return this.email;
	}
	public void setemail(String email) {
		this.email= email;
	}
	
	public String getphoneNumber() { 
		return this.phoneNumber; 
	}
	public void  setphoneNumber(String phoneNumber) { 
		this.phoneNumber=phoneNumber; 
	}
 	
	public void setPassword(String Password) { 
		this.Password=Password; 
	}
	 
	public String getPassword() {
		return this.Password; 
 	}
	
	public void setUsername(String Username) { 
		this.Username=Username; 
	}
 	public String getUsername() {
		return this.Username; 
 	}

}
