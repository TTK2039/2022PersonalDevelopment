package com.example.demo.entity;

import java.security.Timestamp;

public class Categories {
	private int id;
    private String name;
	private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public Categories(int id, String name, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.createdAt = created_at;
		this.updatedAt = updated_at;
	}
    
	public Categories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Categories(String name) {
		super();
		this.name = name;
	}

	public Categories() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}

