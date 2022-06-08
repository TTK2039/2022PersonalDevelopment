package com.example.demo.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Lunch {
	private int id;
    private String name;
	private int price;
    private int cal;
    private int userId;
    private String createdAt; 
    
	public Lunch() {
		super();
	}
	public Lunch(@NotBlank String name, @NotBlank int price, @NotNull int cal, int userId, String createdAt) {
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.userId = userId;
		this.createdAt = createdAt;
	}
		
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
    
    
	
}

