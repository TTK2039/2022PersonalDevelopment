package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Categories;

@Repository
public class PgCategoryDao implements CategoryDao {

	private static final String SQL_SELECT_CATEGORY ="SELECT * from categories";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Categories> categoryList() {
		String sql = SQL_SELECT_CATEGORY;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Categories>(Categories.class));

	}
}