package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserRepoUserDetailSrevice usd; 
	@Bean
	public PasswordEncoder encoder() { 
		return new  BCryptPasswordEncoder(); 
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usd)
		.passwordEncoder(encoder()); 
	}
	@Override
	protected void  configure(HttpSecurity http ) throws Exception  { 
		http.authorizeRequests()
		.antMatchers("/design" ,"/orders")
		.access("hasRole('ROLE_USER')")	
		.antMatchers("/", "/**").access("permitAll")
		.and()
		.formLogin()
	     .loginPage("/login")
		.defaultSuccessUrl("/design", true)
   		.usernameParameter("username")
		.passwordParameter("password")
;
		
 	}
}
