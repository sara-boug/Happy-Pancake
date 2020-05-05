package com.example.demo.repository_interface;

import com.example.demo.Ingredient;

public interface IngredientRepo {
	Iterable<Ingredient> findAll(); 
	Ingredient findOne(Long id); 
	Ingredient save(Ingredient ing); 

}
