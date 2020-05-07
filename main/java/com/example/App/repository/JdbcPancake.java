package com.example.App.repository;

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
		for (Integer i : pancake.getingredient()) {
			this.saveToPancake_Ingredient(i, pancakeId);
		}

		return pancake;
	}

	private Long saveToPancake(Pancake pancake) {
		pancake.setcreateAt(new Date());
		PreparedStatementCreatorFactory pscf=new PreparedStatementCreatorFactory(
				"insert into Pancake(name , createAt) values(?,?)", Types.VARCHAR, Types.DATE);
		pscf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc = pscf
						.newPreparedStatementCreator(Arrays.asList(pancake.getname(),
								pancake.getcreatedAt()));
		System.out.println("psc" + psc);
		KeyHolder keyholder = new GeneratedKeyHolder();
		template.update(psc, keyholder);
		System.out.println("Key" + " " + keyholder.getKey().longValue());
		return keyholder.getKey().longValue();
	}

	private void saveToPancake_Ingredient(int ingredientId, Long pancakeId) {
		template.update("insert into Ingredient_Pancake( id_Ingredient, id_Pancake ) " + "values (?,?)", ingredientId,
				pancakeId);

	}

}
