package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.BentoDao;
import com.example.demo.entity.Bento;

@Repository
public class PgBentoDao implements BentoDao {

	private static final String SQL_FIND_ALL ="SELECT * FROM bento WHERE userid = 1 OR userid = :id";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Bento> findAll (int id) {
		String sql = SQL_FIND_ALL;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Bento>(Bento.class));		
	}

	public Bento findById(int id) {
		String sql = "SELECT * FROM bento WHERE id = :id";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		List<Bento> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Bento>(Bento.class));
		return resultList.isEmpty() ? null : resultList.get(0);

	}

	@Override
	public List<Bento> findUserAll(int id) {
		String sql = "SELECT * FROM bento WHERE userid = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Bento>(Bento.class));		
	}

	@Override
	public int insert(Bento bento) {
		String sql = "INSERT INTO bento (name, price, cal, userId) VALUES(:name, :price, :cal, :id)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", bento.getName());
		param.addValue("price", bento.getPrice());
		param.addValue("cal", bento.getCal());
		param.addValue("id", bento.getUserId());
				
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int delete(int id) {
		String sql ="DELETE FROM bento WHERE id = :id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int update(Bento bento) {
		String sql = """
				UPDATE bento
				SET
				name = :name
				,price = :price
				,cal = :cal
				WHERE
				id = :id""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", bento.getId());
		param.addValue("name", bento.getName());
		param.addValue("price", bento.getPrice());
		param.addValue("cal", bento.getCal());

		return jdbcTemplate.update(sql, param);		
	}

}

