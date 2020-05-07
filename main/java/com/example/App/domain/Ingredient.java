package com.example.App.domain;

import java.util.ArrayList;
import java.util.List;

 
public class Ingredient {

	public  int id ; 
	public   String name ; 
	public  Type type; 	
	public static enum Type { 
		  CHOCOLATE, FLAVOR,FRUITS
	}

	public Ingredient(int id , String name ,Type type ) { 
		this.id = id; 
		this.name=name; 
		this.type= type; 
	}
	
	public static  List<Ingredient>  filterByType(List<Ingredient> pancakes , Type type) {
		ArrayList<Ingredient> typePan = new ArrayList<Ingredient>(); 
		for(int i=0; i< pancakes.size() ; i++) { 
			if(pancakes.get(i).type ==type) { 
				typePan.add(pancakes.get(i));
			}
		}
		
		return   typePan; 
		
	}
 

}
