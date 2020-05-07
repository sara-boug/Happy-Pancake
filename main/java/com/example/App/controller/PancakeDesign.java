package com.example.App.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.example.App.domain.Ingredient;
import com.example.App.domain.Order;
import com.example.App.domain.Pancake;
import com.example.App.domain.Ingredient.Type;
import com.example.App.repository.JdbcIngredient;
import com.example.App.repository.JdbcPancake;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class PancakeDesign {
	
	private final  JdbcIngredient  jdbcIngredient  ; 
	private final  JdbcPancake jdbcPancake; 
 	@Autowired
	public PancakeDesign(JdbcIngredient  jdbcIngredient ,  JdbcPancake jdbcPancake ) { 
		this.jdbcIngredient=jdbcIngredient;
		this.jdbcPancake= jdbcPancake; 
	}

 	@ModelAttribute(name ="order")
 	public Order order() { 
 		return new Order(); 
 	}
	@GetMapping
	public String showDesignForm(Model model) {
		displayIng(model);
		model.addAttribute("design", new Pancake());
		return "DesignPancake";

	}

	@PostMapping
	public String processDesign(@ModelAttribute("design") @Valid Pancake design,
			Errors error, Model model,  @ModelAttribute("order") Order order ) {
		displayIng(model);
		if (error.hasErrors()) {
			System.out.print(error);
			return "DesignPancake";
		}
		Pancake pancake=  jdbcPancake.save(design);
 		order.addPancake(pancake);
		

		return "redirect:/orders/current";
	}

	public Model displayIng(Model model) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>(); 
				jdbcIngredient.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type t : types) {
			model.addAttribute(t.toString().toLowerCase(), Ingredient.filterByType(ingredients, t));

		}
		return model;
	}

}
