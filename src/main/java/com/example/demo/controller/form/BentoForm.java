package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.entity.Bento;
public class BentoForm {
	
	private int id;
	@NotBlank
	private String name;
    @NotNull
	private int price;
    @NotNull
    private int cal;
    @NotNull
    private int userId;
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userid) {
		this.userId = userid;
	}
	public String getName() {
		return name;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Bento getAll() {
		return new Bento(id, name, price, cal, userId);
	}
    
}

