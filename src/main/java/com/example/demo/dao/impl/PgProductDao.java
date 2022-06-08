package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Repository
public class PgProductDao implements ProductDao {

	private static final String TEST = "SELECT time created_at, heavy id FROM test";
	private static final String LIKE_PNAME ="p.name LIKE :pname";
	private static final String ID = "p.id = :id";
	private static final String LIKE_CNAME = "c.name LIKE :cname";
	private static final String SQL_DELETE = "DELETE FROM products p";
	private static final String SQL_UPDATE = """
			UPDATE products
			SET
			product_id = :pname
			,name = :name
			,price = :price
			,category_id = :cid
			,description = :description
			WHERE
			id = :id""";
	private static final String SQL_INSERT = """
			INSERT INTO products (
			product_id
			, name
			, price
			, category_id
			, description
			, created_at
			, updated_at
			) VALUES(
			:id
			,:name
			,:price
			,:cId
			,:description
			,current_timestamp
			, current_timestamp)""";
	private static final String SQL_SELECT_ALL_JOIN = """
			SELECT
			p.id id
			, p.product_id product_id
			, p.name name
			, p.price price
			, p.description description
			,c.name category_name
			, p.created_at
			FROM products p 
			JOIN categories c 
			ON p.category_id = c.id""";



	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> findAll() {
		String sql = SQL_SELECT_ALL_JOIN;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

	}

	@Override
	public Product findById(int id) {
		String sql = SQL_SELECT_ALL_JOIN + " WHERE " +ID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public int register(Product pd) {
		String sql = SQL_INSERT;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", pd.getProductId());
		param.addValue("name", pd.getName());
		param.addValue("price", pd.getPrice());
		param.addValue("cId", pd.getCategoryId());
		param.addValue("description", pd.getDescription());

		return jdbcTemplate.update(sql, param);

	}

	@Override
	public int delete(int id) {
		String sql = SQL_DELETE + " WHERE " +ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		return jdbcTemplate.update(sql, param);
	}

	@Override
	public List<Product> findByKey(String key) {
		String sql = SQL_SELECT_ALL_JOIN;

		if(key == "") {
			return this.findAll();
		}else {
			sql += " WHERE " + LIKE_PNAME + " OR " + LIKE_CNAME;

			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("pname", "%" + key + "%");
			param.addValue("cname", "%" + key + "%");


			return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		}
	}

	@Override
	public int update(Product pd) {
		String sql = SQL_UPDATE;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("pname", pd.getProductId());
		param.addValue("name", pd.getName());
		param.addValue("price", pd.getPrice());
		param.addValue("cid", pd.getCategoryId());
		param.addValue("description", pd.getDescription());
		param.addValue("id", pd.getId());

		return jdbcTemplate.update(sql, param);
	}

	@Override
	public List<Product> test() {
		String sql = TEST;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

	}

}

