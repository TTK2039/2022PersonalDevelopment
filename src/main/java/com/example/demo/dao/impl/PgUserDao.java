package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Repository
public class PgUserDao implements UserDao {

	private static final String SQL_SELECT_ID_PASS= "SELECT * FROM users where login_id = :id AND password = :pass";
	private static final String SQL_INSERT = "INSERT INTO users (login_id, password, name, role) VALUES (:login_id, :pass, :name, 2)";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User loginCheck(User user) {
		String sql = SQL_SELECT_ID_PASS;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", user.getLoginId());
		param.addValue("pass", user.getPassword());

		List<User> rs = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return rs.isEmpty() ? null : rs.get(0);
	}
	
	@Override
	public int createUser(User user) {
		try{String sql = SQL_INSERT;
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", user.getLoginId());
		param.addValue("pass", user.getPassword());
		param.addValue("name", user.getName());
		
		return jdbcTemplate.update(sql, param);}
		catch (Exception e) {
			return 0;
		}
	}
}

