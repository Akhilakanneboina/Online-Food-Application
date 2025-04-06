<%@ page import="com.model.Orders" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="ordercon.css">
</head>
<body>
    <h2>Order Confirmation</h2>

    <%
        Orders order = (Orders) request.getAttribute("order");
        if (order != null) {
    %>
        <p>Order ID: <%= order.getOrderId() %></p>
        <p>Total Amount: <%= order.getTotalAmount() %></p>
        <p>Status: <%= order.getStatus() %></p>
        <p>Payment Mode: <%= order.getPaymentMode() %></p>
    <%
        } else {
    %>
        <p>Error: Order details not available.</p>
    <%
        }
    %>

    <a href="HomeServlet">Return to Home</a>
</body>
</html>
