/*package com.example.App.AsyncMessaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
 
import com.example.App.domain.Order;

public class SendOrder  implements SendOrderInterface{
    private JmsTemplate jmsTemplate; 
    private Destination orderQueue; 
    @Autowired
	public SendOrder(JmsTemplate jmsTemplate, Destination orderQueue) {
		this.jmsTemplate  = jmsTemplate; 
		this.orderQueue= orderQueue; 
		
 	}
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
*/