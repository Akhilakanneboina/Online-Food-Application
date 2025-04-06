package com.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.interfaces.OrderItemsDao;
import com.model.OrderItems;
import com.model.Orders;
import com.model.Menu;

public class OrderItemsImplementation implements OrderItemsDao {

    @Override
    public OrderItems addOrderItem(OrderItems orderItem) {
        if (orderItem == null || orderItem.getOrder() == null || orderItem.getMenuItem() == null) {
            System.out.println("Error: OrderItem, Order, or MenuItem is NULL! Cannot add order item.");
            return null;
        }

        String sql = "INSERT INTO order_items (orderId, menuItemId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, orderItem.getOrder().getOrderId()); // Ensure order is not null
            stmt.setLong(2, orderItem.getMenuItem().getMenuId()); // Ensure menuItem is not null
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getTotalPrice());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    orderItem.setOrderItemId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItems> getOrderItemsByOrder(Long orderId) {
        List<OrderItems> orderItemsList = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE orderId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getLong("orderId"));

                Menu menuItem = new Menu();
                menuItem.setMenuId(rs.getLong("menuItemId"));

                OrderItems orderItem = new OrderItems();
                orderItem.setOrderItemId(rs.getLong("orderItemId"));
                orderItem.setOrder(order); // Set order object
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setTotalPrice(rs.getDouble("totalPrice"));

                orderItemsList.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        String sql = "DELETE FROM order_items WHERE orderItemId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, orderItemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
