package com.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.interfaces.MenuDao;
import com.model.Menu;
import com.model.Restaurant;


public class MenuImplementation implements MenuDao {

    @Override
    public Menu addMenu(Menu menu) {
        String sql = "INSERT INTO menu (restaurantId, itemName, description, price, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, menu.getRestaurant().getRestaurantId());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setDouble(4, menu.getPrice());
            stmt.setBoolean(5, menu.isAvailable());
            stmt.setString(6, menu.getImagePath());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    menu.setMenuId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Menu getMenu(Long menuId) {
        String sql = "SELECT * FROM menu WHERE menuId = ?";
        Menu menu = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, menuId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                menu = new Menu(
                    rs.getLong("menuId"),
                    new Restaurant(rs.getLong("restaurantId")),
                    rs.getString("itemName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Menu updateMenu(Long menuId, Menu menu) {
        String sql = "UPDATE menu SET restaurantId = ?, itemName = ?, description = ?, price = ?, isAvailable = ?, imagePath = ? WHERE menuId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, menu.getRestaurant().getRestaurantId());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setDouble(4, menu.getPrice());
            stmt.setBoolean(5, menu.isAvailable());
            stmt.setString(6, menu.getImagePath());
            stmt.setLong(7, menuId);

            stmt.executeUpdate();
            menu.setMenuId(menuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void deleteMenu(Long menuId) {
        String sql = "DELETE FROM menu WHERE menuId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, menuId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getMenuByRestaurant(Long restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE restaurantId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, restaurantId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getLong("menuId"),
                    new Restaurant(rs.getLong("restaurantId")),
                    rs.getString("itemName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imagePath")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
