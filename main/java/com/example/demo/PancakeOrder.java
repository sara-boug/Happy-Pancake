package com.example.demo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class PancakeOrder {
	 
	@GetMapping("/current")
	public  String  Currentorder(Model model ) {		
		model.addAttribute("order", new Order());		
		return "orderForm"; 
		
	}
  
	@PostMapping
	public String postOrder( @ModelAttribute("order") @Valid Order order , Errors error) { 
		if(error.hasErrors()) { 
			return "orderForm";
		}
		System.out.println(order.getcreditCard());
		return"redirect:/"; 
 	}
}
