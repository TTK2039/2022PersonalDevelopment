package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Lunch;
import com.example.demo.entity.User;

public interface LunchDao {

	public int mogumogu(Lunch lunch);

	public List<Lunch> findByUserId(int id);

	public List<Lunch> findGroupDate(int id);

	public Lunch findById(int id);

	public List<Lunch> findByDay(String day);

	public List<Lunch> findByKey(String key, User user);

	public int delete(int id);

	public int update(Lunch all);

}

