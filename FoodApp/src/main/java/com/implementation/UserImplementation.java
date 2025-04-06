package com.implementation;

import com.config.DatabaseConnection;
import com.interfaces.UserDao;
import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImplementation implements UserDao {

	@Override
	public User addUser(User user) {
		 String sql = "INSERT INTO users (userName, password, email, phone, address, role, createDate, lastLoginDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		        stmt.setString(1, user.getUserName());
		        stmt.setString(2, user.getPassword());
		        stmt.setString(3, user.getEmail());
		        stmt.setString(4, user.getPhone());
		        stmt.setString(5, user.getAddress());
		        stmt.setString(6, user.getRole());
		        if (user.getCreateDate() != null) {
		            stmt.setDate(7, new java.sql.Date(user.getCreateDate().getTime()));
		        } else {
		            stmt.setNull(7, java.sql.Types.DATE);
		        }
		        
		        if (user.getLastLoginDate() != null) {
		            stmt.setDate(8, new java.sql.Date(user.getLastLoginDate().getTime()));
		        } else {
		            stmt.setNull(8, java.sql.Types.DATE);
		        }

		        stmt.executeUpdate();
		        ResultSet rs = stmt.getGeneratedKeys();
		        if (rs.next()) {
		            user.setUserId(rs.getLong(1));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return user;
		
	
	}

	@Override
	public User getUser(Long userId) {
		
		String sql = "SELECT * FROM users WHERE userId = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setLong(1, userId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new User(
	                rs.getLong("userId"),
	                rs.getString("userName"),
	                rs.getString("password"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("address"),
	                rs.getString("role"),
	                rs.getDate("createDate"),
	                rs.getDate("lastLoginDate")
	                );
	           
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public User updateUser(Long userId, User user) {
		String sql = "UPDATE users SET userName = ?, password = ?, email = ?, phone = ?, address = ?, role = ?, createDate = ?, lastLoginDate = ? WHERE userId = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, user.getUserName());
	        stmt.setString(2, user.getPassword());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getPhone());
	        stmt.setString(5, user.getAddress());
	        stmt.setString(6, user.getRole());
	        if (user.getCreateDate() != null) {
	            stmt.setDate(7, new java.sql.Date(user.getCreateDate().getTime()));
	        } else {
	            stmt.setNull(7, java.sql.Types.DATE);
	        }
	        
	        if (user.getLastLoginDate() != null) {
	            stmt.setDate(8, new java.sql.Date(user.getLastLoginDate().getTime()));
	        } else {
	            stmt.setNull(8, java.sql.Types.DATE);
	        }
	        stmt.executeUpdate();
	        user.setUserId(userId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void deleteUser(Long userId) {
		String sql = "DELETE FROM users WHERE userId = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setLong(1, userId);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public List<User> getAllUsers() {
		 List<User> users = new ArrayList<>();
		    String sql = "SELECT * FROM users";
		    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		        while (rs.next()) {
		            users.add(new User(
		                rs.getLong("userId"),
		                rs.getString("userName"),
		                rs.getString("password"),
		                rs.getString("email"),
		                rs.getString("phone"),
		                rs.getString("address"),
		                rs.getString("role"),
		                rs.getDate("createDate"),
		                rs.getDate("lastLoginDate")
		            ));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return users;
		
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new User(
	                rs.getLong("userId"),
	                rs.getString("userName"),
	                rs.getString("password"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("address"),
	                rs.getString("role"),
	                rs.getDate("createDate"),
	                rs.getDate("lastLoginDate")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
		
	}

   
}
