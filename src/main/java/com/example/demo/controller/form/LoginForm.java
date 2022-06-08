package com.example.demo.controller.form;
import javax.validation.constraints.NotBlank;

import com.example.demo.entity.User;
public class LoginForm {
	
	@NotBlank
	private String loginId;
    @NotBlank
	private String password;
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    	
	public User getLoginCheck() {
		return new User(loginId, password);
	}
}

