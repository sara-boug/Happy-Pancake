package com.example.App.AsyncMessaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.example.App.domain.Order;

@Service
public class JmsOrderMessaging  implements OrderMessagingService{
    private JmsTemplate jmsTemplate; 
    private Destination orderQueue; 
    @Autowired
	public JmsOrderMessaging(JmsTemplate jmsTemplate, Destination orderQueue) {
		this.jmsTemplate  = jmsTemplate; 
		this.orderQueue= orderQueue; 
		
 	}
    @GetMapping("/convertOrder/orders")
	@Override
	public String sendOrder(Order order) {
		jmsTemplate.convertAndSend(orderQueue , order,this::postProcessMessage);
		return "converted and sent order"; 
	}
    
	private Message postProcessMessage(Message message) throws JMSException {
		message.setStringProperty("X_ORDER_SOUCE", "WEB");
		return message; 
		} 


}
