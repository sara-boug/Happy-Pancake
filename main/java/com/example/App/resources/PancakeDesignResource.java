package com.example.App.resources;

import java.util.ArrayList;
import java.util.Date;
import com.example.App.domain.Pancake;

import org.springframework.hateoas.RepresentationModel;

 
@SuppressWarnings("rawtypes")
public class PancakeDesignResource extends RepresentationModel{
    private final String name; 
    private final Date createdAt; 
    private final ArrayList<Integer> ingredient ;
    
	public PancakeDesignResource(Pancake pancake) {
		this.name= pancake.getname(); 
		this.createdAt= pancake.getcreatedAt(); 
		this.ingredient= pancake.getingredient(); 
	}
	
	public String getname() { 
		return this.name; 
	}
	
	public Date getcreatedAt() { 
		return this.createdAt; 
	}
	public ArrayList<Integer> getingredient(){ 
		return this.ingredient; 
	}

}
