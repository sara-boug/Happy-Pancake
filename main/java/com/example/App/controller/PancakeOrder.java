package com.example.App.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.App.domain.Order;
import com.example.App.domain.User;
import com.example.App.repository.JdbcPancakeOrder;
import com.example.App.repository.JdbcUser;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class PancakeOrder {
	private JdbcPancakeOrder jdbcPancakeOrder; 
	private JdbcUser jdbcUser; 
	@Autowired
	public PancakeOrder(JdbcPancakeOrder jdbcPancakeOrder , JdbcUser jdbcUser) { 
		this.jdbcPancakeOrder=jdbcPancakeOrder; 
		this.jdbcUser= jdbcUser; 
	}
	@GetMapping
	public  String  Currentorder(Model model ) {		
		model.addAttribute("order", new Order());		
		return "orderForm"; 
		
	}
  
	@PostMapping
	public String postOrder( @ModelAttribute("order") @Valid Order order 
			, Errors error , SessionStatus  status , @AuthenticationPrincipal User user) { 
		if(error.hasErrors()) {
 			return "orderForm";
		}
	    int userId= jdbcUser.findUserId( user.getemail()) ;
	    System.out.println("the user id " +  userId); 
		order.setid_users(userId);
	    this.jdbcPancakeOrder.save(order);
		status.isComplete(); 
		return"redirect:/"; 
 	}
}
