package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Lunch;

public interface LunchDao {

	public int mogumogu(Lunch lunch);

	public List<Lunch> findByUserId(int id);

	public List<Lunch> findGroupDate(int id);

	public Lunch findById(int id);

}

