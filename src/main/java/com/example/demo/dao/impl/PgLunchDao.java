package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.LunchDao;
import com.example.demo.entity.Lunch;
import com.example.demo.entity.User;

@Repository
public class PgLunchDao implements LunchDao {
	//select * from lunch where created_at BETWEEN '2022-04-23' AND '2022-06-08' AND userid = 1 ORDER BY created_at;
	private static final String SQL_INSERT ="INSERT INTO lunch(name, price, cal, userId, created_at) VALUES(:name, :price, :cal, :userId, cast(:time as date))";
	private static final String SQL_SELECT_ALL ="SELECT * FROM lunch";
	private static final String ORDER_BY_DATE =" ORDER BY created_at";
	private static final String SQL_SELECT_ORDER_BY_DATE ="SELECT created_at, sum(price) price, sum(cal) cal FROM lunch WHERE userid = :id GROUP BY created_at ORDER BY created_at";
	private static final String WHERE_USER_ID =" WHERE userid = :id";	
	private static final String WHERE_LUNCH_ID =" WHERE id = :id";
	private static final String WHERE_CREATEDAT =" WHERE created_at = cast(:day as date) AND userId = :id";
	private static final String LIKE_NAME ="name LIKE :name";
	private static final String SQL_DELETE = "DELETE FROM lunch WHERE id = :id";
	private static final String SQL_FIND_BY_BETWEEN ="select * from lunch where created_at BETWEEN cast(:day1 as date) AND cast(:day2 as date) AND userid = :id ORDER BY created_at;";
	private static final String SQL_UPDATE = """
			UPDATE lunch
			SET
			name = :name
			,price = :price
			,cal = :cal
			,created_at = :time
			WHERE
			id = :id""";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Override
	public int update(Lunch lunch) {
		String sql = SQL_UPDATE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", lunch.getName());
		param.addValue("price", lunch.getPrice());
		param.addValue("cal", lunch.getCal());
		param.addValue("time", lunch.getCreatedAt());
		param.addValue("id", lunch.getId());

		return jdbcTemplate.update(sql, param);		
	}

	@Override
	public int delete(int id) {
		String sql = SQL_DELETE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);

		return jdbcTemplate.update(sql, param);		
	}

	@Override
	public int mogumogu(Lunch lunch) {
		String sql = SQL_INSERT;
		//INSERT INTO lunch(name, price, cal, userId, created_at) VALUES( :name, :price, :cal, :userId, cast(:time as date))
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		param.addValue("name", lunch.getName());
		param.addValue("price", lunch.getPrice());
		param.addValue("cal", lunch.getCal());
		param.addValue("userId", lunch.getUserId());
		param.addValue("time", lunch.getCreatedAt());



		return jdbcTemplate.update(sql, param);		
	}

	@Override
	public List<Lunch> findByDay(String day, User user) {
		if(day == "") {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id", user.getId());
			return jdbcTemplate.query(SQL_SELECT_ALL+ WHERE_USER_ID, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}else {
			String sql = SQL_SELECT_ALL + WHERE_CREATEDAT;

			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("day", day);
			param.addValue("id", user.getId());
			return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}
	}

	@Override
	public List<Lunch> findByDayRange(String day1, String day2, User user) {
		if(day1 == "" || day2 =="") {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id", user.getId());
			return jdbcTemplate.query(SQL_SELECT_ALL+ WHERE_USER_ID, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}else {
			String sql = SQL_FIND_BY_BETWEEN;

			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("day1", day1);
			param.addValue("day2", day2);		
			param.addValue("id", user.getId());
			return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}
	}

	@Override
	public List<Lunch> findByUserId(int id) {
		//SELECT * FROM lunch WHERE userid = :id ORDER BY created_at
		String sql = SQL_SELECT_ALL+ WHERE_USER_ID + ORDER_BY_DATE + " LIMIT 10";

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

	@Override
	public List<Lunch> findByKey(String key, User user) {
		String sql = SQL_SELECT_ALL+ WHERE_USER_ID;

		if(key == "") {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id", user.getId());
			return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}else {
			sql += " AND " + LIKE_NAME + ORDER_BY_DATE;

			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("name", "%" + key + "%");
			param.addValue("id", user.getId());
			return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Lunch>(Lunch.class));
		}
	}





}

