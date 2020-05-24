package com.example.App.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.App.domain.Order;
import com.example.App.repository_interface.PancakeOrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JdbcPancakeOrder implements PancakeOrderRepo {
	private SimpleJdbcInsert orderInsert;
	private SimpleJdbcInsert pancakeOrderInsert;
 	private ObjectMapper objectMapper;
 	private JdbcTemplate template; 

	@Autowired
	public JdbcPancakeOrder(JdbcTemplate template) {
		this.orderInsert = new SimpleJdbcInsert(template).withTableName("PancakeOrder").usingGeneratedKeyColumns("id");
		this.pancakeOrderInsert = new SimpleJdbcInsert(template).withTableName("PancakeOrder_Pancake");
		this.objectMapper = new ObjectMapper();
		this.template=template; 
	}

	@Override
	public Order save(Order order)  {
		order.setplacedAt(new Date());
		ArrayList<Integer> pancakes = order.getPancake();
		long orderId = savePancakeOrderDetails(order);
		for (Integer element : pancakes) {
			saveToPancake_PancakeOrder(element, orderId);

		}
		return order;
	}

	@SuppressWarnings({ "unchecked" })
	private long savePancakeOrderDetails(Order order) {
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getplacedAt());
		long orderId = orderInsert.executeAndReturnKey(values).longValue();
		return orderId;
	}

	private void saveToPancake_PancakeOrder( int  pancake, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("id_Pancake", pancake);
		values.put("id_PancakeOrder", orderId);
		pancakeOrderInsert.execute(values);

	}
	public ArrayList<Order> getAll( int id ){ 
		return (ArrayList<Order>) template.query("select  id , name , street , zipCode, state , city , creditCard , cvv , placedAt"
				+ "  from PancakeOrder where  id_Users=?" , this::mapRowOrder ,id);
	}

	private Order mapRowOrder(ResultSet rs , int rowNum) throws SQLException {
		return  new Order(
			rs.getLong("id"),
			rs.getString("name"), 
			rs.getString("street"), 
			rs.getString("zipCode"), 
			rs.getString("state"), 
			rs.getString("city"), 
			rs.getString("creditCard"),
			rs.getString("cvv"),
			rs.getDate("placedAt")
			); 
			
			
		}; 

	public  void deleteOne(int id ) { 
		template.execute("delete from PancakeOrder where id="+ id ); 
		
	}
	
	public Order findById( int id ){ 
		return template.queryForObject("select  id , name , street , zipCode, state , city , creditCard , cvv , placedAt"
				+ "  from PancakeOrder where  id_Users=?" , this::mapRowOrder ,id);
	}
	public void  update( String values , int id ) { 
	    template.execute("update PancakeOrder set "+  values + "where id ="+ id); 
	}
}
