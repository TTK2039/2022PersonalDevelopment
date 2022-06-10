package com.example.demo.entity;

public class Bento {
	private int id;
    private String name;
	private int price;
    private int cal;
    private int userId;
    
	public Bento() {
		super();
	}
	
	
	
	public Bento(int id, String name, int price, int cal, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.userId = userId;
	}



	public Bento(String name, int price, int cal, int userId) {
		super();
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.userId = userId;
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

