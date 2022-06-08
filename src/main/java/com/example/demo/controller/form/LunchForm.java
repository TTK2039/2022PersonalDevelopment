package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.entity.Lunch;
public class LunchForm {
	
	@NotBlank
	private String name;
    @NotNull
	private int price;
    @NotNull
    private int cal;
    @NotNull
    private int userId;
    @NotBlank
    private String createdAt;
    
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

	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Lunch getAll() {
		return new Lunch(name, price, cal, userId, createdAt);
	}
}

