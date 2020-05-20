package com.example.App.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.App.domain.User;
import com.example.App.domain.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

 
public class JwtAuthentificationTokenFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JwtAuthentificationTokenFilter(AuthenticationManager authenticationManager) {
 		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)   {
		try {
 
        	UserLogin credentials =  new ObjectMapper().readValue( req.getInputStream(), UserLogin.class); 

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
                               credentials.getemail(), 
                               credentials.getpassword(), 
                               new ArrayList<>())
					); 

		} catch (IOException e) {
            throw new RuntimeException(e); 
		}
	}
	@Override
	protected void  successfulAuthentication(HttpServletRequest req , HttpServletResponse res 
			                                 , FilterChain chain , Authentication auth ) { 
		@SuppressWarnings("unused")
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getemail())				
			     .withExpiresAt( new Date(System.currentTimeMillis()+ SecurityConstant.expirationTime) )
		     	.sign(HMAC512(SecurityConstant.secretKey.getBytes())); 
		String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYXJhYm91Z0BnbWFpbC5jb20ifQ.Vbzz-JTWQd1wFFoagvjYcxe7VsHs30rjowLb6pqNb1gSpp-TWu18Fbuf-74uPzA7cJo8ak5gwm3aOxNebz_OkQ";
		res.addHeader(SecurityConstant.headerString, SecurityConstant.tokenPrefix + testToken);	
 	}

}
