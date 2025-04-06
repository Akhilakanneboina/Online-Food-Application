<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.interfaces.OrderDao, com.implementation.OrderImplementation, com.model.Orders, com.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    OrderDao orderDAO = new OrderImplementation();
    List<Orders> userOrders = orderDAO.getOrdersByUser(user.getUserId());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Your Orders</title>
    <link rel="stylesheet" href="order.css">
</head>
<body>
    <header>
        <h1>Your Orders</h1>
    </header>

    <main>
        <ul>
            <% for (Orders order : userOrders) { %>
                <li>
                    Order ID: <%= order.getOrderId() %> <br>
                    Total: $<%= order.getTotalAmount() %> <br>
                    Status: <%= order.getStatus() %> <br>
                    <a href="orderdetails.jsp?orderId=<%= order.getOrderId() %>">View Details</a>
                </li>
            <% } %>
        </ul>
    </main>
</body>
</html>
