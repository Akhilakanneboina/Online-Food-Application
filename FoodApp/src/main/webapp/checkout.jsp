<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.CartItem" %>
<%@ page import="com.model.Menu" %>
<%@ page import="com.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Checkout</title>
    <link rel="stylesheet" href="checkout.css">
</head>
<body>

<%
    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
    if (cartItems == null || cartItems.isEmpty()) {
        System.out.println("Cart is empty! Redirecting back to cart.jsp.");
        response.sendRedirect("cart.jsp");
        return;
    }

    // Get restaurant from first cart item
    Restaurant restaurant = (cartItems.size() > 0) ? cartItems.get(0).getMenu().getRestaurant() : null;
    if (restaurant == null) {
        System.out.println("Error: Restaurant is null. Redirecting back to cart.jsp.");
        response.sendRedirect("cart.jsp?error=restaurantMissing");
        return;
    }
%>

<header>
    <h1>Checkout</h1>
</header>

<main>
    <section class="order-summary">
        <h2>Order Summary</h2>
        <ul>
            <%
                double totalPrice = 0;
                for (CartItem item : cartItems) {
                    Menu menu = item.getMenu();
                    totalPrice += menu.getPrice() * item.getQuantity();
            %>
            <li>
                <%= menu.getItemName() %> - Quantity: <%= item.getQuantity() %> - Price: $<%= menu.getPrice() * item.getQuantity() %>
            </li>
            <% } %>
        </ul>
        <h3>Total Price: $<%= totalPrice %></h3>
    </section>

    <section class="customer-info">
        <h2>Enter Delivery Details</h2>
        <form action="PlaceOrderServlet" method="post">
            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="address">Address:</label>
            <textarea id="address" name="address" required></textarea>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required>

            <!-- Payment Mode -->
            <label for="paymentMode">Payment Mode:</label>
            <select id="paymentMode" name="paymentMode" required>
                <option value="Cash on Delivery">Cash</option>
                <option value="Credit Card">Credit_Card</option>
                <option value="Cash on Delivery">Debit_Card</option>
                <option value="Credit Card">Upi</option>
                <option value="Cash on Delivery">Wallet</option>
                
            </select>

            <!-- Hidden fields -->
            <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
            <input type="hidden" name="totalAmount" value="<%= totalPrice %>">

            <button type="submit" class="place-order-btn">Place Order</button>
        </form>
    </section>
</main>

</body>
</html>
