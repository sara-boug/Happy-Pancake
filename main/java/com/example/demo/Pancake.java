package com.example.demo;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Pancake {
	@NotNull
	@Size(min=5 , message="insert longeer name please")
	private String name; 
	/*@NotNull
	@Size(min=1, message="at least one ingredient")*/
	private ArrayList<String> ingredient; 
	
	
	public String getname() { 
		return this.name; 
	}
	
	public void setname(String name) { 
		this.name= name; 
	}
	
	public ArrayList<String> getingredient() { 
		return this.ingredient; 
	}
	
	public void setingredient(String ingredient) { 
		this.ingredient.add(ingredient); 
	}


}
