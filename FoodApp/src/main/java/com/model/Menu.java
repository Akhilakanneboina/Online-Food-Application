package com.model;

public class Menu {
    private Long menuId;
    private Restaurant restaurant;
    private String itemName;
    private String description;
    private double price;
    private boolean isAvailable;
    private String imagePath;
public Menu() {
	// TODO Auto-generated constructor stub
}
    // ✅ Constructor with only menuId
    public Menu(Long menuId) {
        this.menuId = menuId;
    }

    // ✅ Full Parameterized Constructor
    public Menu(Long menuId, Restaurant restaurant, String itemName, String description, double price,
                boolean isAvailable, String imagePath) {
        this.menuId = menuId;
        this.restaurant = restaurant;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    // ✅ Getters and Setters
    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    // ✅ Cleaned-up toString() Method
    @Override
    public String toString() {
        return "Menu {" +
                "menuId=" + menuId +
                ", restaurant=" + (restaurant != null ? restaurant.getRestaurantId() : "null") +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
