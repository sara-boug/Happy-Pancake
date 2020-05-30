package com.example.App.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
	public  long id ; 
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
	public Ingredient(  String name ,Type type ) { 
	 
		this.name=name; 
		this.type= type; 
	}
	public Ingredient() { 
		
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
	
	public long getid() { 
		return this.id; 
	}
	public void setid(long id ) { 
		this.id = id; 
	}
	public void setname(String name ) { 
		this.name = name; 
	}
	public String getname() { 
		return this.name; 
	}
	public Type gettype() { 
		return this.type; 
	} 
	public void settype(Type type) { 
		this.type= type; 
	}
	
	  @Override
	  public String toString() {  // used to map the content of the response body 
	    return "Value{" +
	        "id=" + id +
	        ", name='" + name+ '\'' +
	        '}';
	  }

  
}
