package com.example.App.repository_interface;

import com.example.App.domain.Ingredient;

public interface IngredientRepo {
	Iterable<Ingredient> findAll(); 
	Ingredient findOne(Long id); 
	Ingredient save(Ingredient ing); 

}
