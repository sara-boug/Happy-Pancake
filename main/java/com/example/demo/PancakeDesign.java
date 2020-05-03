package com.example.demo;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Ingredient.Type;

@Controller
@RequestMapping("/design")
public class PancakeDesign {
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
			return "DesignPancake";
		}
		System.out.print(design.getingredient());

		return "redirect:/orders/current";
	}

	public Model displayIng(Model model) {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient(1, "dark chocolate", Type.CHOCOLATE),
				new Ingredient(2, "white chocolte", Type.CHOCOLATE), new Ingredient(3, "vanilla", Type.FLAVOR),
				new Ingredient(4, "coffee", Type.FLAVOR), new Ingredient(5, "Bananas", Type.FRUITS),
				new Ingredient(6, "strawberries", Type.FRUITS));

		Type[] types = Ingredient.Type.values();
		for (Type t : types) {
			model.addAttribute(t.toString().toLowerCase(), Ingredient.filterByType(ingredients, t));

		}
		return model;
	}

}
