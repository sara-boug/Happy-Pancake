package com.example.App.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.example.App.RestApiController.IngredientApi;
import com.example.App.domain.Ingredient;

@Relation(value="ingredient" ,collectionRelation="href")
public class IngredientAssembler 
 extends  RepresentationModelAssemblerSupport <Ingredient , IngredientResources>{

	public IngredientAssembler() {
		super( IngredientApi.class ,  IngredientResources.class);
 	}
	public CollectionModel< IngredientResources> toCollecModel(ArrayList<Ingredient>  ingred) {
	   	 ArrayList< IngredientResources>   iteration= new ArrayList<IngredientResources>(); 
		 CollectionModel<IngredientResources> result = new CollectionModel<IngredientResources>(iteration); 
		 for(Ingredient i : ingred) { 
			 iteration.add( this.toModel(i)); 
	    	 result.add(
				linkTo(methodOn(IngredientApi.class).getById(i.getId()))
				.withSelfRel()
				); 
 
		 }
    	 result.add(
			linkTo(methodOn(IngredientApi.class).getAll())
			.withSelfRel()
			); 

		return result ;
	}
	
	@Override
	public IngredientResources  instantiateModel(Ingredient ingredient) { 
		return new IngredientResources(ingredient); 
	}


	@Override
	public IngredientResources toModel(Ingredient ingredient) {
 		return new IngredientResources(ingredient);
	}

}
