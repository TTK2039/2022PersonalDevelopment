package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;

import com.example.demo.entity.User;
public class SignUpForm {
	
	@NotBlank
	private String userName;
    @NotBlank
	private String userId;
    @NotBlank
    private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
	public User getAll() {
		return new User(userId, password, userName);
	}
}

