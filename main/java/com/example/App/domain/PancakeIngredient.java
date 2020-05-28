package com.example.App.domain;

public class PancakeIngredient {
    private  long idPancake  ; 
    private long idIngredient; 
	public PancakeIngredient( long idPancake,  long idIngredient) {
		this.idPancake = idPancake ;
		this.idIngredient= idIngredient; 
	}
	
	public long getidPancake() {
		return this.idPancake; 
	}
	
	public long getIngredient() { 
		return this.idIngredient; 
	}
  
}
