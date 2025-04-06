package com.model;

import java.time.LocalDateTime;

public class Orders {
    private Long orderId;
    private User user;
    private Restaurant restaurant;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;
    private String paymentMode;

    public Orders(long orderId) {
    	this.orderId=orderId;
    }

    public Orders(Long orderId, User user, Restaurant restaurant, LocalDateTime orderDate, double totalAmount,
                  String status, String paymentMode) {
        this.orderId = orderId;
        this.user = user;
        this.restaurant = restaurant;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public Orders() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", user=" + user + ", restaurant=" + restaurant + ", orderDate="
				+ orderDate + ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMode=" + paymentMode
				+ ", getOrderId()=" + getOrderId() + ", getUser()=" + getUser() + ", getRestaurant()=" + getRestaurant()
				+ ", getOrderDate()=" + getOrderDate() + ", getTotalAmount()=" + getTotalAmount() + ", getStatus()="
				+ getStatus() + ", getPaymentMode()=" + getPaymentMode() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	// Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}
