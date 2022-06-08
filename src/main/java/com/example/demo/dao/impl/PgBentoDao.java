package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.BentoDao;
import com.example.demo.entity.Bento;

@Repository
public class PgBentoDao implements BentoDao {

	private static final String SQL_FIND_ALL ="SELECT * FROM bento";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Bento> findAll () {
		String sql = SQL_FIND_ALL;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Bento>(Bento.class));		
	}
	

}

