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
    @NotBlank
    private String password2;
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
   
	public User getAll() {
		return new User(userId, password, userName);
	}
}

