package com.longhust.service;

import com.longhust.controller.UserController;
import com.longhust.model.User;

import java.util.List;

public class UserService {
    private UserController userController;

    public UserService() {
        userController = new UserController();
    }

    public List<User> getAllUsers() {
        return userController.getAllUsers();
    }

    public void addUser(User user) {
        userController.addUser(user);
    }

    public void deleteUser(int id) {
        userController.deleteUser(id);
    }

    public User getUserById(int id) {
        return  userController.getUserById(id);
    }

    public void updateUser(User user) {
        userController.updateUser(user);
    }
}
