<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.model.CartItem, com.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
    <header>
    <h1>Your Cart</h1>
    <div class="header-buttons">
        <a href="MenuServlet" class="button-link">Back To Menu</a>
    </div>
</header>
    

    <main>
        <section class="cart">
            <h2>Cart Items</h2>
            <ul>
                <%
                    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
                    if (cartItems != null && !cartItems.isEmpty()) {
                        for (CartItem item : cartItems) {
                            Menu menu = item.getMenu();
                %>
                <li class="cart-item">
                    <img src="<%= menu.getImagePath() != null ? menu.getImagePath() : "default.png" %>" 
                         alt="<%= menu.getItemName() %>" width="100" height="100">
                    <h3><%= menu.getItemName() %></h3>
                    <p>Price: $<%= menu.getPrice() %></p>
                    <p>Quantity: <%= item.getQuantity() %></p>
                    <p>Total: $<%= item.getTotalPrice() %></p>

                    <form action="RemoveFromCartServlet" method="post">
                        <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                        <button type="submit" class="remove-btn">Remove</button>
                    </form>
                </li>
                <%
                        }
                    } else {
                %>
                <p>Your cart is empty.</p>
                <%
                    }
                %>
            </ul>

            <% if (cartItems != null && !cartItems.isEmpty()) { %>
                <form action="CheckOutServlet" method="get">
                    <button type="submit" class="checkout-btn">Proceed to Checkout</button>
                </form>
            <% } %>
        </section>
    </main>

 
</body>
</html>
