package com.implementation;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.interfaces.RestaurantDao;
import com.model.Restaurant;


public class RestaurantImplementation implements RestaurantDao {

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant (name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisineType());
            stmt.setInt(3, restaurant.getDeliveryTime());
            stmt.setString(4, restaurant.getAddress());
            stmt.setLong(5, restaurant.getAdminUserId());
            stmt.setDouble(6, restaurant.getRating());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setString(8, restaurant.getImagePath());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    restaurant.setRestaurantId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public Restaurant getRestaurant(Long restaurantId) {
        String sql = "SELECT * FROM restaurant WHERE restaurantId = ?"; // ✅ Use correct table name
        Restaurant restaurant = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, restaurantId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Restaurant Found: " + rs.getString("name")); // ✅ Debug log

                restaurant = new Restaurant(
                    rs.getLong("restaurantId"),
                    rs.getString("name"),
                    rs.getString("cuisineType"),
                    rs.getInt("deliveryTime"),
                    rs.getString("address"),
                    rs.getLong("adminUserId"),
                    rs.getDouble("rating"),
                    rs.getBoolean("isActive"),
                    rs.getString("imagePath")
                );
            } else {
                System.out.println("Error: Restaurant NOT found for ID: " + restaurantId);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getRestaurant: " + e.getMessage()); // ✅ Better error handling
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        String sql = "UPDATE restaurant SET name = ?, cuisineType = ?, deliveryTime = ?, address = ?, adminUserId = ?, rating = ?, isActive = ?, imagePath = ? WHERE restaurantId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisineType());
            stmt.setInt(3, restaurant.getDeliveryTime());
            stmt.setString(4, restaurant.getAddress());
            stmt.setLong(5, restaurant.getAdminUserId());
            stmt.setDouble(6, restaurant.getRating());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setString(8, restaurant.getImagePath());
            stmt.setLong(9, restaurantId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                restaurant.setRestaurantId(restaurantId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        String sql = "DELETE FROM restaurant WHERE restaurantId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, restaurantId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                restaurants.add(new Restaurant(
                    rs.getLong("restaurantId"),
                    rs.getString("name"),
                    rs.getString("cuisineType"),
                    rs.getInt("deliveryTime"),
                    rs.getString("address"),
                    rs.getLong("adminUserId"),
                    rs.getDouble("rating"),
                    rs.getBoolean("isActive"),
                    rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
