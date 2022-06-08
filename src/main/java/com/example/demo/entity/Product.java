package com.example.demo.entity;

import java.sql.Timestamp;

public class Product {

    private int id;
    private String productId;
    private int categoryId;
    private String name;
    private int price;
    private String imagePath;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    private Categories category;

    public Product() {
		super();
	}
    
    public Product(String productId,String name,int price, int categoryId, String description) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.description = description;
	}



	public Product(Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Product(String productId, String name, int price, int categoryId, String description, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    	
    	
    

	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setCategoryName(String categoryName) {
		if(this.category == null) {
			this.category = new Categories();
		}
		category.setName(categoryName);
	}
    
    public String getCategoryName() {
    	return category.getName();
    }
}
