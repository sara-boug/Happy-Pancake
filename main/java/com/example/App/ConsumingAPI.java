package com.example.App;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.App.domain.Ingredient;

public class ConsumingAPI {
RestTemplate rest ; 
Traverson traverson; 
	public ConsumingAPI() {
		rest = new RestTemplate(); 
		traverson = new Traverson(URI.create("http://localhost:8080/ingredient") , MediaTypes.HAL_JSON); 
 	}
 	public Ingredient getIngredientById(String IngredientId) { 

		Map<String , String> urlVariable= new HashMap<String, String>(); 
	    urlVariable.put("id", IngredientId); 
	    System.out.println(urlVariable);
	    URI url = UriComponentsBuilder
	    		.fromHttpUrl("http://localhost:8080/ingredient/{id}")
	    		.build(urlVariable);
	    return rest.getForObject(url, Ingredient.class); 
	}
 	
 	public Ingredient postIngredient(Ingredient ing) { 
 				return  rest.postForObject("http://localhost:8080/ingredient"
 				, ing,
 				 Ingredient.class);
  		
 	}

}
