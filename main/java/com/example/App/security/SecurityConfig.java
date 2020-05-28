package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserRepoUserDetailService usd; 
	@Bean
	public PasswordEncoder encoder() { 
		return new  BCryptPasswordEncoder(); 
	}
	@Bean 
	CorsConfigurationSource corsConfig() { 
		UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();  
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source; 
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usd)
		.passwordEncoder(encoder()); 
	}
	@Override
	protected void  configure(HttpSecurity http ) throws Exception  { 
		 http.cors().and().csrf().disable().authorizeRequests()
		 .antMatchers("/design", "/design/**", "/order/**", "/ingredient/**")	
		 .authenticated()	
		 .antMatchers("/", "/**").anonymous()
		 .and()
		 .addFilter(new JwtAuthentificationTokenFilter(authenticationManager()))
		 .addFilter(new TokenVerification(authenticationManager()))
		 .logout( ) ;
  	}
}
