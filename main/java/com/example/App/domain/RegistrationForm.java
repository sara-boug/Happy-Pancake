package com.example.App.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {
    	
	@NotNull
    @Size(min = 3, max = 100, message= "Username must be between 3 and 200 characters")
	private String Username; 
	@NotNull
	@Email(message="insert a valid email")
	private String email ; 
	@NotNull
	@Pattern(regexp ="(06|07|05){1}\\d{8}$" , message= "insert a valid phone number")
	private String phoneNumber; 
	@NotNull
    @Size(min = 4, max = 100, message= "insert a longer password between 3 and 200 characters")
	private String Password;
	@NotNull
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
