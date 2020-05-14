package com.example.App.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {

	private String Username; 
	private String email ; 
	private String phoneNumber; 
	private String Password;
	private String confirmedPassword; 
  
	public User toUser( PasswordEncoder passwordEncoder) { 
		return new User(
				this.Username.toString().toLowerCase().trim(),
				this.email.toString().toLowerCase().trim() ,
				this.phoneNumber.toString().toLowerCase().trim(),
				passwordEncoder.encode( this.Password.trim())); 
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
	
	public void setconfirmedPassword(String confirmedPassword) { 
		this.confirmedPassword=confirmedPassword; 
	}
	 
	public String getconfirmedPassword() {
		return this.confirmedPassword; 
 	}

	
	public void setUsername(String Username) { 
		this.Username=Username; 
	}
 	public String getUsername() {
		return this.Username; 
 	}

}
