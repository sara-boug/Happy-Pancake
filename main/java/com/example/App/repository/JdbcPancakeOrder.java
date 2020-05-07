package com.example.App.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.example.App.domain.Order;
import com.example.App.domain.Pancake;
import com.example.App.repository_interface.PancakeOrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JdbcPancakeOrder implements PancakeOrderRepo {
	private SimpleJdbcInsert orderInsert;
	private SimpleJdbcInsert pancakeOrderInsert;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcPancakeOrder(JdbcTemplate template) {
		this.orderInsert = new SimpleJdbcInsert(template).withTableName("PancakeOrder").usingGeneratedKeyColumns("id");
		this.pancakeOrderInsert = new SimpleJdbcInsert(template).withTableName("PancakeOrder_Order");
		this.objectMapper = new ObjectMapper();

	}

	@Override
	public Order save(Order order) {
        order.setplacedAt(new Date());
        ArrayList<Pancake> pancakes=order.getPancake(); 
        long orderId=savePancakeOrderDetails(order); 
        for(Pancake element :pancakes) { 
        	saveToPancake_PancakeOrder(element, orderId); 
        	
        }
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	private long savePancakeOrderDetails(Order order) {
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPancake());
		long orderId = orderInsert.executeAndReturnKey(values).longValue();
		return orderId;
	}

	private void saveToPancake_PancakeOrder(Pancake pancake, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put(" id_Pancake", pancake.getid());
		values.put("id_PancakeOrder", orderId);
		pancakeOrderInsert.execute(values);

	}

}
