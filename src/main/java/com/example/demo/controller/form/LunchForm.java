package com.example.demo.controller.form;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.example.demo.entity.Lunch;
public class LunchForm {
	
	
	private int id;

	private String name;
	
	private String name2;
	@PositiveOrZero
    @NotNull
	private int price;
	@PositiveOrZero
	@NotNull
    private int cal;
    @NotNull
    private int userId;
    @NotNull
    private Date createdAt;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Lunch getAll() {
		return new Lunch(id, name, price, cal, userId, createdAt);
	}
	
	public Lunch getAll2() {
		return new Lunch(id, name2, price, cal, userId, createdAt);
	}
	
}

