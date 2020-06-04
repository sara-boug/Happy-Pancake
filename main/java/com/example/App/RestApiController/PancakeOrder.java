package com.example.App.RestApiController;
 
import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.App.domain.Order;
import com.example.App.repository.JdbcPancakeOrder;
import com.example.App.repository.JdbcUser;
 
@Service
@RestController
@RequestMapping( path= "/order"  , produces="application/json")
@CrossOrigin("*")
public class PancakeOrder {
    JdbcPancakeOrder jdbcPancakeOrder; 
    JdbcUser jdbcUser; 
    JmsTemplate jmsTemplate; 
  

     @Autowired
	public PancakeOrder(JdbcPancakeOrder jdbcPancakeOrder , JdbcUser jdbcUser ,JmsTemplate jmsTemplate  ) {
		this.jdbcPancakeOrder = jdbcPancakeOrder; 
		this.jdbcUser= jdbcUser; 
		this.jmsTemplate= jmsTemplate;
 
 	}
	
	
	@PostMapping( path="/current/{pancake}" , consumes= "application/json")
	public ResponseEntity<Order> PostOrder(@RequestBody Order order  , @PathVariable int pancake)  { 
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		    int userId= jdbcUser.findUserId(auth.getPrincipal().toString()) ;
		    System.out.println(order); 
		   	jmsTemplate.convertAndSend("kitchen" , order , this::postProcessMessage);

			order.setid_users(userId);
	    	ArrayList<Integer>  pancakes= new ArrayList<>(); 
	    	pancakes.add(pancake);
			order.setPancake(pancakes);
			System.out.println("ABOUT TO CONVERT AND SEND"); 

 	     	return  new ResponseEntity<>(this.jdbcPancakeOrder.save(order) , HttpStatus.OK);  
		} catch(Exception e ) { 
       	    return  new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);  		
		}
	
	}
	private Message postProcessMessage(Message message) throws JMSException {
		message.setStringProperty("X_ORDER_SOUCE", "WEB");
 		return message; 
		} 

	
	@GetMapping(path="/all")
	public  ResponseEntity< ArrayList<Order>> getAll() { 
		try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
 	    int userId= jdbcUser.findUserId(auth.getPrincipal().toString()) ;
		   return new ResponseEntity<>( jdbcPancakeOrder.getAll(userId) ,HttpStatus.OK); 
		}catch( Exception e ) {
     		return  new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);  

		}

	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deletePancake(@PathVariable int  id ) { 
		try {
		  jdbcPancakeOrder.deleteOne(id); 
		   return new ResponseEntity<>(HttpStatus.OK); 
		}catch (Exception e) { 
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 

		}
	}
	
 	@PatchMapping(path="/update/{orderid}")
	public  ResponseEntity<Order> patchOrder( @RequestBody Order bodyOrder,  @PathVariable int orderid)  {
		try {
		 String values = ""; 
		 if(bodyOrder.getname()!=null) { 
			  values+="name =" + bodyOrder.getname();
		 }
		 if(bodyOrder.getstreet()!=null) { 
			  values+="street =" +bodyOrder.getstreet();
		 }
		 if(bodyOrder.getzipCode()!=null) { 
			values+="zipCode =" + bodyOrder.getzipCode();
		 }
		 if(bodyOrder.getstate()!=null) { 
			 values+="state =" +   bodyOrder.getstate() ;
		 }
		 if(bodyOrder.getcity()!=null) { 
			  values+= "city =" + bodyOrder.getcity();
		 }
		 if(bodyOrder.getcreditCard()!=null) { 
			  values+= "creditCard =" + bodyOrder.getcreditCard();
		 }
		 if(bodyOrder.getcvv()!=null) { 
			values+="cvv =" +bodyOrder.getcvv();
		 }
			this.jdbcPancakeOrder.update(values,orderid);
			 Order order = this.jdbcPancakeOrder.findById(orderid); 

     		return  new ResponseEntity<>(order , HttpStatus.OK);  
		} catch(Exception e ) { 
     		return  new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);  
		}
		
	}

}
