package com.model;

public class CartItem {
    private Long cartItemId;
    private Long userId;
    private Menu menu;
    private int quantity;

    // Constructors
    public CartItem() {}

    public CartItem(Menu menu, int quantity) {
        this.userId = userId;
        this.menu = menu;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getCartItemId() { return cartItemId; }
    public void setCartItemId(Long cartItemId) { this.cartItemId = cartItemId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return this.menu.getPrice() * this.quantity;
    }
}
