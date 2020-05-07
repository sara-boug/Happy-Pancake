package com.example.App.domain;

import java.util.Date;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Pancake {
	private  long id; 
	private Date createdAt; 
	@NotNull
	@Size(min=5 , message="insert longeer name please")
	private String name; 
	@NotNull
	@Size(min=1, message="at least one ingredient")
	private ArrayList<Integer> ingredient; 
	private  int oneIngredient ; 
	
	public long getid() { 
		return this.id; 
	}	
	public void setid(long id ) { 
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
	public int getoneIngredient() { 
		return this.oneIngredient; 
	}
	
	public void setoneIngredient(int  oneIngredient) {
		this.oneIngredient= oneIngredient;

	}
	

	 public ArrayList<Integer> getingredient() { 
		return this.ingredient; 
	}
	
   public void setingredient(ArrayList<Integer> ingredient) { 
  		this.ingredient = ingredient; 
 
	 
	}


}
