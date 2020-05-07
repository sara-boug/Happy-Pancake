package com.example.App.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.App.repository.JdbcPancakeOrder;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class PancakeOrder {
	private JdbcPancakeOrder jdbcPancakeOrder; 
	@Autowired
	public PancakeOrder(JdbcPancakeOrder jdbcPancakeOrder) { 
		this.jdbcPancakeOrder=jdbcPancakeOrder; 
	}
	@GetMapping("/current")
	public  String  Currentorder(Model model ) {		
		model.addAttribute("order", new Order());		
		return "orderForm"; 
		
	}
  
	@PostMapping
	public String postOrder( @ModelAttribute("order") @Valid Order order 
			, Errors error , SessionStatus  status) { 
		if(error.hasErrors()) {
			System.out.print(error);
			return "orderForm";
		}
	    this.jdbcPancakeOrder.save(order);
		status.isComplete(); 
		return"redirect:/"; 
 	}
}
