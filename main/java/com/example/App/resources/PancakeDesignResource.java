package com.example.App.resources;


import java.util.Date;
import com.example.App.domain.Pancake;
import com.example.App.resources.IngredientAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

 
@SuppressWarnings("rawtypes")
public class PancakeDesignResource extends RepresentationModel{
	private static final  IngredientAssembler ingredientAssembler = new IngredientAssembler();
    private final String name; 
    private final Date createdAt; 
    private final CollectionModel<IngredientResources> ingredient ;
    
	public PancakeDesignResource(Pancake pancake) {
		this.name= pancake.getname(); 
		this.createdAt= pancake.getcreatedAt(); 
		this.ingredient =  ingredientAssembler.toCollecModel(pancake.getingredient());

	}
	
	public String getname() { 
		return this.name; 
	}
	
	public Date getcreatedAt() { 
		return this.createdAt; 
	}
	public CollectionModel<IngredientResources> getingredient(){ 
		return this.ingredient; 
	}

}
