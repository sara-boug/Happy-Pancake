package com.example.App.RestApiController;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.repository.JdbcIngredient;
import com.example.App.resources.IngredientAssembler;
import com.example.App.resources.IngredientResources;
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
	public CollectionModel<IngredientResources>getAll() { 
	  ArrayList<Ingredient> ingredient=  (ArrayList<Ingredient>) jdbcIngredient.findAll(); 	
	  List<IngredientResources> resource = new ArrayList<IngredientResources>(); 
	  for(Ingredient ing: ingredient) { 
		  resource.add(new IngredientAssembler().toModel(ing)) ;
	  }
	  CollectionModel<IngredientResources> result= new CollectionModel<IngredientResources>(resource); 
	  result.add(
			  linkTo(
					  methodOn(IngredientApi.class).getAll()
					  
					)
			  .withSelfRel()
			  );
	  return result; 
	}	
	
	@PostMapping(consumes="application/json")
	public Ingredient save(@RequestBody Ingredient ing) {
  		return jdbcIngredient.save(ing); 		
	}
	@GetMapping("/{id}")
	public Ingredient  getById(@PathVariable("id") long id ) {
		return jdbcIngredient.findOne(id); 
		 
		
	}
	
	
	
	

}
