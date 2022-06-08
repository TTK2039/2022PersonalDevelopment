package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.example.demo.entity.Product;
public class RegisterForm {
	
	@NotBlank
	private String pdId;
    @NotBlank
	private String pdName;
    @NotNull
    @PositiveOrZero
    private int price;
    @PositiveOrZero
    private int roleId;
    
    private String description;
    
    private String images;

	public String getPdId() {
		return pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
    
	public Product getAll() {
		return new Product(pdId, pdName, price, roleId, description);
	}
}

