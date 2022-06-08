package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.LunchDao;
import com.example.demo.entity.Lunch;

@Repository
public class PgLunchDao implements LunchDao {

	private static final String SQL_INSERT ="INSERT INTO lunch(name, price, cal, userId, created_at) VALUES( :name, :price, :cal, :userId, cast(:time as date))";
	private static final String SQL_SELECT_ALL ="SELECT * FROM lunch";
	private static final String ORDER_BY_DATE =" ORDER BY created_at";
	private static final String SQL_SELECT_ORDER_BY_DATE ="SELECT created_at, sum(price) price, sum(cal) cal FROM lunch WHERE userid = :id GROUP BY created_at ORDER BY created_at";
	private static final String WHERE_USER_ID =" WHERE userid = :id";	
	private static final String WHERE_LUNCH_ID =" WHERE id = :id";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int mogumogu(Lunch lunch) {
		String sql = SQL_INSERT;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", lunch.getName());
		param.addValue("price", lunch.getPrice());
		param.addValue("cal", lunch.getCal());
		param.addValue("userId", lunch.getUserId());
		param.addValue("time", lunch.getCreatedAt());

		
		
		return jdbcTemplate.update(sql, param);		
	}

	@Override
	public List<Lunch> findByUserId(int id) {
		String sql = SQL_SELECT_ALL+ WHERE_USER_ID + ORDER_BY_DATE;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
	}

	@Override
	public List<Lunch> findGroupDate(int id) {
		String sql = SQL_SELECT_ORDER_BY_DATE;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
	}

	@Override
	public Lunch findById(int id) {
		String sql = SQL_SELECT_ALL + WHERE_LUNCH_ID;
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		List<Lunch> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		return resultList.isEmpty() ? null : resultList.get(0);
		
	}
	

}

