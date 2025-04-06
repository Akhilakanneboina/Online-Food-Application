package com.model;

import java.util.Date;

public class User {
    private long userId;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String role;
    private Date createDate;
    private Date lastLoginDate;
    public User() {
    	
    }

    // Default Constructor
    public User(long userId) {
    	this.userId=userId;
    	
    }

    public User(Long userId, String userName, String password, String email, String phone, String address, String role) {
        this.userId = (userId != null) ? userId : 0; // Handle null case
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }


    // Parameterized Constructor
    

    public User(long userId, String userName, String password, String email, String phone, String address, String role,
			Date createDate, Date lastLoginDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}

	// Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
                + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createDate=" + createDate
                + ", lastLoginDate=" + lastLoginDate + "]";
    }
}
