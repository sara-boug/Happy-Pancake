package com.example.App.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.App.domain.Pancake;
import com.example.App.repository_interface.PancakeRepo;

@Repository
public class JdbcPancake implements PancakeRepo {
	JdbcTemplate template;

	public JdbcPancake(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Pancake save(Pancake pancake) {
		long pancakeId = saveToPancake(pancake);
		for (int i : pancake.getingredient()) {
			this.saveToPancake_Ingredient(i, pancakeId);
		}

		return pancake;
	}

	private Long saveToPancake(Pancake pancake) {
		pancake.setcreateAt(new Date());
		PreparedStatementCreatorFactory pscf=new PreparedStatementCreatorFactory(
				"insert into Pancake(name , createdAt) values(?,?)", Types.VARCHAR, Types.DATE);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc = pscf
						.newPreparedStatementCreator(Arrays.asList(pancake.getname(),
								pancake.getcreatedAt()));
		KeyHolder keyholder = new GeneratedKeyHolder();
		template.update(psc, keyholder);
		return keyholder.getKey().longValue();
	}

	private void saveToPancake_Ingredient(int ingredientId, Long pancakeId) {
		template.update("insert into Ingredient_Pancake( id_Ingredient, id_Pancake ) " + "values (?,?)", ingredientId,
				pancakeId);

	}
	
	public Iterable<Pancake>  findAll() { 
		return template.query("select   id , name , createdAt from pancake", this::rowMapper);
		
	}
	private Pancake rowMapper(ResultSet rs , int rowNum) throws SQLException { 
		return new Pancake( 
				rs.getInt("id"), 
				rs.getDate("createdAt"), 
				rs.getString("name")
				); 
	}
	
	public Pancake findById(int id) { 
		return template.queryForObject("select   id , name , createdAt from pancake where id = ? ", this::rowMapper,id ); 
	}
}
