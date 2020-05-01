package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Ingredient.Type;
 
 
@Controller
@RequestMapping("/design")
public class PancakeDesign {
	@GetMapping
	public String showDesignForm(Model model) { 
         List<Ingredient> ingredients = Arrays.asList(
        		 new Ingredient(1, "dark chocolate",Type.CHOCOLATE),
       	 	     new Ingredient(2, "white chocolte", Type.CHOCOLATE),
        		 new Ingredient(3, "vanilla", Type.FLAVOR),
        		 new Ingredient(4, "coffee", Type.FLAVOR),
        		 new Ingredient(5, "Bananas", Type.FRUITS),
        		 new Ingredient(6, "strawberries", Type.FRUITS)
       		); 		
		
		Type[] types = Ingredient.Type.values(); 
		for(Type t:types) { 		
			System.out.print(t.toString());
			model.addAttribute(t.toString().toLowerCase() ,Ingredient.filterByType(ingredients, t)); 
		}
 		return "DesignPancake" ;  
		
 	}


}
