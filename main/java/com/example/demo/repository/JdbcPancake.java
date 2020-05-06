package com.example.demo.repository;


import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.Ingredient;
import com.example.demo.Pancake;
import com.example.demo.repository_interface.PancakeRepo;

@Repository
public class JdbcPancake implements PancakeRepo{
     JdbcTemplate template ;  
     public  JdbcPancake( JdbcTemplate template ) { 
    	 this.template= template ;
     }
     
	@Override
	public Pancake save(Pancake pancake) {
		long pancakeId= saveToPancake(pancake); 
		for(Integer i : pancake.getingredient()) { 
			 this.saveToPancake_Ingredient(i, pancakeId);
		}
		 
		return pancake;
	}
	
	private Long saveToPancake(Pancake pancake) { 
	   	pancake.setcreateAt(new Date());
	   	PreparedStatementCreator psc = new PreparedStatementCreatorFactory(	   	 
	   	"insert into Pancake(name , createAt) values(?,?)").newPreparedStatementCreator(
	   			Arrays.asList(pancake.getname(), pancake.getcreatedAt())
	   			);
		KeyHolder keyholder = new GeneratedKeyHolder();
		template.update(psc , keyholder);
		return keyholder.getKey().longValue(); 
 	}
	 
	
	private void saveToPancake_Ingredient(int ingredientId , Long pancakeId) { 
 		 template.update("insert into Ingredient_Pancake( id_Ingredient, id_Pancake ) "
		 		+ "values (?,?)" , ingredientId, pancakeId); 
		
	}

}
