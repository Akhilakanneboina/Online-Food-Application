package com.model;

public class OrderItems {
    private Long orderItemId;
    private Orders order;
    private Menu menuItem;
    private int quantity;
    private double totalPrice;

    public OrderItems() {}

    public OrderItems(Long orderItemId, Orders order, Menu menuItem, int quantity, double totalPrice) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    @Override
	public String toString() {
		return "OrderItems [orderItemId=" + orderItemId + ", order=" + order + ", menuItem=" + menuItem + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", getOrderItemId()=" + getOrderItemId() + ", getOrder()="
				+ getOrder() + ", getMenuItem()=" + getMenuItem() + ", getQuantity()=" + getQuantity()
				+ ", getTotalPrice()=" + getTotalPrice() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	// Getters and Setters
    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }
    public Orders getOrder() { return order; }
    public void setOrder(Orders order) { this.order = order; }
    public Menu getMenuItem() { return menuItem; }
    public void setMenuItem(Menu menuItem) { this.menuItem = menuItem; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}