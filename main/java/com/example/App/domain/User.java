package com.example.App.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String Username; 
	private String email ; 
	private String phoneNumber; 
	private String Password; 
    public User(String Username,String email , String phoneNumber , String Password) { 
    	this.Username= Username; 
    	this.email=email; 
        this.Password=Password; 
        this.phoneNumber=phoneNumber; 
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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPassword(String Password) { 
		this.Password=Password; 
	}
	@Override
	public String getPassword() {
		return this.Password; 
 	}
	
	public void setUsername(String Username) { 
		this.Username=Username; 
	}
	@Override
	public String getUsername() {
		return this.Username; 
 	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


}
