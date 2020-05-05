package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Ingredient;
import com.example.demo.repository_interface.IngredientRepo;


@Repository
public class JdbcIngredient implements IngredientRepo{
    private JdbcTemplate template; 
    public JdbcIngredient(JdbcTemplate template) {  
    	this.template=template;
    }
	@Override
	public Iterable<Ingredient> findAll() {
		 return template.query("select id, name, types from Ingredient",
				 this::mapRowIngredient);
  	}

	@Override
	public Ingredient findOne(Long id) {
		return template.queryForObject("select id , name, types from Ingredient where id=?" , this::mapRowIngredient ,id);
		 
	}

	@Override
	public Ingredient save(Ingredient ing) {
		template.update("insert into Ingredient (id , name , types) values (?,?,?)" , 
				ing.id, ing.name, ing.type); 
 		return ing;
	}
	
	
	private Ingredient mapRowIngredient(ResultSet rs , int rowNum) throws SQLException {
		return  new Ingredient (
			rs.getInt("id"),
			rs.getString("name"), 
			Ingredient.Type.valueOf(rs.getString("types"))); 
			
			
		}; 
		
	}

 
