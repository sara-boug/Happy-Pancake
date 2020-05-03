package com.example.demo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

public class Order {
  @NotBlank(message="name not blank")
   private String  name ; 
  @NotBlank(message="street not blank")

   private String  street ; 
  @NotBlank(message=" zipCode not blank")

   private String zipCode; 
  @NotBlank(message="state not blank")

   private String state; 
  @NotBlank(message="city not blank")

   private String city ; 
  @CreditCardNumber(message="credit card number not blank")

   private String creditCard; 
  @NotBlank(message="cvv not blank")

   private String cvv; 
   
   
	public String getname() { 
		return this.name; 
	}	
	public void setname(String name) { 
		this.name= name; 
	}
	
	
	public String getstreet() { 
		return this.street; 
	}	
	
	public void setstreet(String street) { 
		this.street= street; 
	}

	
	public String getstate() { 
		return this.state; 
	}	
	public void setstate(String state) { 
		this.state= state; 
	}

	
	public String getcity() { 
		return this.city; 
	}	
	public void setcity(String city) { 
		this.city= city; 
	}

	
	public String getcreditCard() { 
		return this.creditCard; 
	}	
	public void setcreditCard(String creditCard) { 
		this.creditCard= creditCard; 
	}

	
	public String getcvv() { 
		return this.cvv; 
	}	
	public void setcvv(String cvv ) { 
		this.cvv=cvv; 
	}

	public String getzipCode() { 
		return this.zipCode; 
	}	
	public void setzipCode(String zipCode) { 
		this.zipCode= zipCode; 
	}




}
