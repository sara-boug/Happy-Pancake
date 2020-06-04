package com.example.App.AsyncMessaging;

import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import com.example.App.domain.Order;
@Configuration
public class MessagingConfig  {

	public MessagingConfig() {
 	}
	@Bean 
	public  JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory , 
			DefaultJmsListenerContainerFactoryConfigurer configurer){ 
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory(); 
		configurer.configure(factory, connectionFactory);
		return factory; 
	}
	
	@Bean
    public Destination orderQueue() { 
    	return  new ActiveMQQueue("pancakeCloud.order.queue");
    }
	
	@Bean 
	public MessageConverter  convert( ) { 
 		MappingJackson2MessageConverter messageConverter= new MappingJackson2MessageConverter(); 
		messageConverter.setTypeIdPropertyName("_typeId");
		 Map<String,Class<?>> typeIdMapping = new HashMap<String,Class<?>>(); 
		typeIdMapping.put("order", Order.class); 
	
		messageConverter.setTypeIdMappings(typeIdMapping); 
		return messageConverter; 
		

		
	}
}
