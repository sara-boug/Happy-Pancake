package com.example.App.domain;

import java.util.Date;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 public class Pancake {
 	private  int id; 
	private Date createdAt; 
	@NotNull
	@Size(min=5 , message="insert longer name please !")
	private String name; 
	@NotNull
	@Size(min=1, message="at least one ingredient !")
 	private ArrayList<Ingredient> ingredient; 
	public Pancake(int id , Date  createdAt, String name ) {
		this.id= id ; 
		this.createdAt  = createdAt ; 
		this.name= name; 
	 	}

	public long getid() { 
		return this.id; 
	}	
	public void setid(int id ) { 
		this.id= id; 
	}
	
	public Date getcreatedAt() { 
		return this.createdAt; 
	}	
 	public void setcreateAt(Date date ) { 
		this.createdAt= date; 
	}
	

	
	public String getname() { 
		return this.name; 
	}
	
	public void setname(String name) { 
		this.name= name; 
	}

	

	 public ArrayList<Ingredient> getingredient() { 
		return this.ingredient; 
	}
	
   public void setingredient(ArrayList<Ingredient> ingredient) { 
  		this.ingredient = ingredient; 
 
	 
	}


}
