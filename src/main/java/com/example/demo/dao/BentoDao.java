package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Bento;

public interface BentoDao {

	public List<Bento> findAll(int i);

	public Bento findById(int id);

	public List<Bento> findUserAll(int id);

	public int insert(Bento bento);

	public int delete(int id);

	public int update(Bento all);

}

