package com.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.config.DatabaseConnection;
import com.interfaces.OrderDao;

import com.model.Orders;
import com.model.User;
import com.model.Restaurant;


public class OrderImplementation implements OrderDao {

    @Override
    public Orders addOrder(Orders order) {
    	
    	    if (order == null || order.getUser() == null) {
    	        System.out.println("Error: Order or User is null! Cannot add order.");
    	        return null; // Prevent NullPointerException
    	    }

    	    String sql = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
    	    try (Connection conn = DatabaseConnection.getConnection();
    	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

    	        stmt.setLong(1, order.getUser().getUserId()); // Ensure user is not null
    	        stmt.setLong(2, order.getRestaurant().getRestaurantId());
    	        stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
    	        stmt.setDouble(4, order.getTotalAmount());
    	        String status = order.getStatus().toUpperCase().trim(); // Convert to uppercase
    	        if (!Arrays.asList("PENDING", "CONFIRMED", "DELIVERED", "CANCELLED").contains(status)) {
    	            status = "PENDING"; // Default value
    	        }
    	        stmt.setString(5, status);

    	        String paymentMode = order.getPaymentMode().toUpperCase().trim(); // Convert to uppercase
    	        if (!Arrays.asList("CASH", "CREDIT_CARD", "DEBIT_CARD", "UPI", "WALLET").contains(paymentMode)) {
    	            paymentMode = "CASH"; // Default to CASH if invalid
    	        }
    	        stmt.setString(6, paymentMode);


    	        int affectedRows = stmt.executeUpdate();
    	        if (affectedRows > 0) {
    	            ResultSet rs = stmt.getGeneratedKeys();
    	            if (rs.next()) {
    	                order.setOrderId(rs.getLong(1));
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return order;
    	}

        
    

    @Override
    public Orders getOrder(Long orderId) {
    	 String sql = "SELECT o.*, u.userId, u.userName FROM orders o JOIN users u ON o.userId = u.userId WHERE o.orderId = ?";
    	    Orders order = null;

    	    try (Connection conn = DatabaseConnection.getConnection();
    	         PreparedStatement stmt = conn.prepareStatement(sql)) {

    	        stmt.setLong(1, orderId);
    	        ResultSet rs = stmt.executeQuery();

    	        if (rs.next()) {
    	            User user = new User();
    	            user.setUserId(rs.getLong("userId"));
    	            user.setUserName(rs.getString("userName")); // Fetch user name

    	            Restaurant restaurant = new Restaurant();
    	            restaurant.setRestaurantId(rs.getLong("restaurantId"));

    	            order = new Orders(
    	                rs.getLong("orderId"),
    	                user, // Now setting full user object
    	                restaurant,
    	                rs.getTimestamp("orderDate").toLocalDateTime(),
    	                rs.getDouble("totalAmount"),
    	                rs.getString("status"),
    	                rs.getString("paymentMode")
    	            );
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return order;
        
    }

    @Override
    public List<Orders> getOrdersByUser(Long userId) {
    	List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT o.*, u.userId, u.userName FROM orders o JOIN users u ON o.userId = u.userId WHERE o.userId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setUserName(rs.getString("userName"));

                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getLong("restaurantId"));

                Orders order = new Orders(
                    rs.getLong("orderId"),
                    user,
                    restaurant,
                    rs.getTimestamp("orderDate").toLocalDateTime(),
                    rs.getDouble("totalAmount"),
                    rs.getString("status"),
                    rs.getString("paymentMode")
                );
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public void deleteOrder(Long orderId) {
        String sql = "DELETE FROM orders WHERE orderId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
