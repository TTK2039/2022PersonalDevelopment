package com.example.demo.entity;

import java.security.Timestamp;

public class User {
	private int userId;
	private String loginId;
	private String password;
	private String name;
	private int role;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public User() {
	}

	public User(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public User(int userId, String loginId, String password) {
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
	}
	
	public User(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}
	
	public User(int id, String logId, String password, String name, int role, Timestamp cr, Timestamp up) {
		this.userId = id;
		this.loginId = logId;
		this.password = password;
		this.name = name;
		this.role = role;
		this.createdAt = cr;
		this.updatedAt = up;
	}
	//    public User(ResultSet rs) {
		//		try {
			//			this.userId = rs.getInt("userId");
			//			this.loginId = rs.getString("loginId");
	//			this.password = rs.getString("password");
	//			this.name = rs.getString("name");
	//			this.role = rs.getInt("role");
	//			this.createdAt = rs.getInt("createdAt");
	//			this.updatedAt = rs.getInt("updatedAt");
	//		} catch (SQLException e) {}
	//	}

	public int getId() {
		return userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
	

}

