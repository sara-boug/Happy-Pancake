package com.example.App.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenVerification  extends BasicAuthenticationFilter{
 	public TokenVerification(AuthenticationManager authenticationManager)  {
 		super(authenticationManager); 
 	}
    @Override   
    protected void doFilterInternal(HttpServletRequest req , HttpServletResponse res ,FilterChain chain  ) throws IOException, ServletException { 
    	String header = req.getHeader(SecurityConstant.headerString); 
    	if(header==null || ! header.startsWith(SecurityConstant.tokenPrefix)) { 
    		chain.doFilter(req, res);
    		return; 
    	}
    	UsernamePasswordAuthenticationToken authentication = getauthentication(req); 
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	chain.doFilter(req, res);
    }
    
    private UsernamePasswordAuthenticationToken getauthentication(HttpServletRequest req ){ 
    	String token  = req.getHeader(SecurityConstant.headerString); 
      	if(token != null) { 
    		
    		String user = JWT.require(Algorithm.HMAC512(SecurityConstant.secretKey.getBytes()))
    				          .build()
    				          .verify(token.replace(SecurityConstant.tokenPrefix , "").trim())
    				           .getSubject(); 
     		if(user !=null) { 
    			return new UsernamePasswordAuthenticationToken(user, null , new ArrayList<>()); 
    		}
    		return null ; 
    	}
    	return null; 
    	
    }
}
