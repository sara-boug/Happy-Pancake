package com.example.App.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.repository.JdbcIngredient;
import com.example.App.domain.Ingredient;

@RestController()
@RequestMapping(path="/ingredient" , produces="application/json")
public class IngredientApi {
     
	private JdbcIngredient  jdbcIngredient; 
	@Autowired
	public IngredientApi( JdbcIngredient  jdbcIngredient) {
		this.jdbcIngredient= jdbcIngredient ;
 	}
	
	
	@GetMapping
	public Iterable<Ingredient> getAll() { 
		return jdbcIngredient.findAll(); 	
	}	
	@GetMapping("/{id}")
	public Ingredient  getById(@PathVariable("id") long id ) {
		return jdbcIngredient.findOne(id); 
		 
		
	}
	
	

}
