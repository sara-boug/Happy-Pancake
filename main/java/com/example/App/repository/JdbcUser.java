package com.example.App.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.App.domain.User;
import com.example.App.repository_interface.UserRepo;

@Repository
public class JdbcUser implements UserRepo {
	JdbcTemplate template;

	public JdbcUser(JdbcTemplate template) {
		this.template = template;

	}

	@Override
	public User findByUserName(String email)  throws EmptyResultDataAccessException  {
		try {	
 		User user =  template.queryForObject("select  fullname, email ,phonenumber , password from users where email =?",
				this::mapRowUserDetail, email.toString().trim());
 		return user;
		}catch(EmptyResultDataAccessException exp) { 
 			return null;
			
		}
	}

	private User mapRowUserDetail(ResultSet rs, int rowNum) throws SQLException {
		if (rs != null) {
			return new User(rs.getString("fullname"), rs.getString("email"), rs.getString("phonenumber"),
					rs.getString("password"));
		} else {
			return null;
		}

	}

	@Override
	public User save(User user) {
		template.update("insert into users( fullname, email ,phonenumber , password ) values (?,?,?,?) ",
				user.getUsername(), user.getemail(), user.getphoneNumber(), user.getPassword());
		return user;
	}

	@Override
	public ArrayList<User> getUsers() {
		return (ArrayList<User>) template.query("select  fullname, email ,phonenumber , password  from Users",
				this::mapRowUserDetail);

	}

	public Boolean existEmail(String email) {

		try {
			template.queryForObject("select  fullname, email ,phonenumber , password from users where email =?",
					this::mapRowUserDetail, email.toString().trim());

			return true;

		} catch (EmptyResultDataAccessException exp) {
			return false;
		}

	}

}
