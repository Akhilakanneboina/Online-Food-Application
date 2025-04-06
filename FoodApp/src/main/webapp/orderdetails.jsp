<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.interfaces.OrderItemsDao, com.implementation.OrderItemsImplementation, com.model.OrderItems" %>

<%
    Long orderId = Long.parseLong(request.getParameter("orderId"));
    OrderItemsDao orderItemsDAO = new OrderItemsImplementation();
    List<OrderItems> orderItems = orderItemsDAO.getOrderItemsByOrder(orderId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Details</title>
</head>
<body>
    <h1>Order Details</h1>
    <ul>
        <% for (OrderItems item : orderItems) { %>
            <li>
                <%= item.getMenuItem().getItemName() %> - 
                Quantity: <%= item.getQuantity() %> - 
                Price: $<%= item.getTotalPrice() %>
            </li>
        <% } %>
    </ul>
</body>
</html>
