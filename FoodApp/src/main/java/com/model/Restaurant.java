package com.model;

// Restaurant Class
public class Restaurant {
    private Long restaurantId;
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private Long adminUserId;
    private double rating;
    private boolean isActive;
    private String imagePath;

    public Restaurant(long restaurantId) {
    	this.restaurantId=restaurantId;
    
    }
    public Restaurant() {
		// TODO Auto-generated constructor stub
	}

    public Restaurant(Long restaurantId, String name, String cuisineType, int deliveryTime, String address,
                      Long adminUserId, double rating, boolean isActive, String imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.isActive = isActive;
        this.imagePath = imagePath;
    }

    public Restaurant(Object setRestaurantId) {
		// TODO Auto-generated constructor stub
	}
	// Getters and Setters
    public Long getRestaurantId() { 
    	return restaurantId; 
    	}
    public void setRestaurantId(Long restaurantId) { 
    	this.restaurantId = restaurantId;
    	}
    public String getName() {
    	return name;
    	}
    public void setName(String name) {
    	this.name = name; 
    	}
    public String getCuisineType() {
    	return cuisineType; 
    	}
    public void setCuisineType(String cuisineType) { 
    	this.cuisineType = cuisineType;
    	}
    public int getDeliveryTime() {
    	return deliveryTime; 
    	}
    public void setDeliveryTime(int deliveryTime) { 
    	this.deliveryTime = deliveryTime; 
    	}
    public String getAddress() {
    	return address;
    	}
    public void setAddress(String address) {
    	this.address = address; 
    	}
    public Long getAdminUserId() {
    	return adminUserId; 
    	}
    public void setAdminUserId(Long adminUserId) { 
    	this.adminUserId = adminUserId; 
    	}
    public double getRating() { 
    	return rating; 
    	}
    public void setRating(double rating) { 
    	this.rating = rating; 
    	}
    public boolean isActive() { 
    	return isActive; 
    	}
    public void setActive(boolean isActive) { 
    	this.isActive = isActive; 
    	}
    public String getImagePath() {
    	return imagePath;
    	}
    public void setImagePath(String imagePath) { 
    	this.imagePath = imagePath;
    	}

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", cuisineType=" + cuisineType +
                ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminUserId=" + adminUserId +
                ", rating=" + rating + ", isActive=" + isActive + ", imagePath=" + imagePath + "]";
    }
}
