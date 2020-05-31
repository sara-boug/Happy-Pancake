package com.example.App.AsyncMessaging;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.example.App.domain.Order;

@Configuration
public class Dest  {

	public Dest() {
 	}
	
	@Bean
    public Destination orderQueue() { 
    	return  new ActiveMQQueue("pancakeCloud.order.queue");
    }
	
	@Bean 
	public MappingJackson2MessageConverter  convert() { 
		MappingJackson2MessageConverter messageConverter= new MappingJackson2MessageConverter(); 
		messageConverter.setTypeIdPropertyName("_typeId");
		Map<String,Class<?>> typeIdMapping = new HashMap<String,Class<?>>(); 
		typeIdMapping.put("order", Order.class); 
		messageConverter.setTypeIdMappings(typeIdMapping);
		return messageConverter; 
	}
}
