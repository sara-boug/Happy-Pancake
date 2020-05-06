package com.example.demo;

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

import com.example.demo.Ingredient.Type;
import com.example.demo.repository.JdbcIngredient;
import com.example.demo.repository.JdbcPancake;

@Controller
@RequestMapping("/design")
public class PancakeDesign {
	
	private final  JdbcIngredient  jdbcIngredient  ; 
	private final  JdbcPancake jdbcPancake; 
 	@Autowired
	public PancakeDesign(JdbcIngredient  jdbcIngredient ,  JdbcPancake jdbcPancake ) { 
		this.jdbcIngredient=jdbcIngredient;
		this.jdbcPancake= jdbcPancake; 
	}
	@GetMapping
	public String showDesignForm(Model model) {
		displayIng(model);
		model.addAttribute("design", new Pancake());
		return "DesignPancake";

	}

	@PostMapping
	public String processDesign(@ModelAttribute("design") @Valid Pancake design, Errors error, Model model) {
		displayIng(model);
		if (error.hasErrors()) {
			System.out.print(error);
			return "DesignPancake";
		}
		System.out.println( design.getingredient()+" ");

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
