package com.example.App.resources;

import org.springframework.hateoas.RepresentationModel;

import com.example.App.domain.Ingredient;
import com.example.App.domain.Ingredient.Type;

@SuppressWarnings("rawtypes")
public class IngredientResources  extends  RepresentationModel {
    private String name ; 
    private  Type type; 
 	public IngredientResources(Ingredient ingredient)  {
		this.name=  ingredient.name;  
		this.type = ingredient.type; 
 	}
 	
 	public String getname() { 
 		return this.name; 
 	}
 	
 	public Type gettype() { 
 		return this.type; 
 	}
}
