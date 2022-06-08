package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class UpdateForm {
	
	@NotBlank
	private String pdId;
    @NotBlank
	private String pdName;
    @NotNull
    private int price;
    @NotNull
    private int roleId;
    
    private String description;

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
    
    
	
}

