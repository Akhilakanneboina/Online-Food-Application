package com.interfaces;

import java.util.List;

import com.model.User;

public interface UserDao {
	User addUser(User user);
    User getUser(Long userId);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    List<User> getAllUsers();
    User getUserByEmailAndPassword(String email, String password);

}
