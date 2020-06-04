package com.example.App.AsyncMessaging;

import javax.jms.JMSException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import com.example.App.domain.Order;

@Component
public class ReceiveOrder implements ReceiveOrderInterface{
	

	@JmsListener(destination = "kitchen" , containerFactory="myFactory")
	public  void receiveOrder(Order o ) throws MessageConversionException, JMSException { 
		System.out.println("Received Somthing" + o.getcity() );
  		
	}
}
