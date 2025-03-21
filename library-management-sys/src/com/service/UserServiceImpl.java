package com.service;

import java.util.List;

import com.dao.UserDAO;
import com.entity.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String addUser(User user) {
        // validate email
        User existingEmail = userDAO.getUserByEmail(user.getEmail());
        if (existingEmail != null) {
            return "User is already registered with this email!";  
        }
      
        //validate contact
        User existingContact = userDAO.getUserByContact(user.getContact());
        if (existingContact != null) {
            return "User is already registered with this contact!";
        }
         
        // validate other fields
        if (user.getFirstName() == null) {
            return "First name cannot be empty";
        }
        else if (user.getLastName() == null) {
            return "Last name cannot be empty";
        }
        else if (user.getCity() == null || user.getState() == null) {
            return "City and state cannot be null!";
        }
        
        return userDAO.addUser(user) ? "User registered successfully!" : "Failed to register user!";
    }

    @Override
    public String updateUser(User user) {
        User existingUser = userDAO.getUser(user.getUserId());
        if (existingUser==null) {
            return "User does not exist!";
        }
        return userDAO.updateUser(user) ? "User updated successfully!" : "Failed to update user!";
    }

    @Override
    public String deleteUser(int userId) {
        User existingUser = userDAO.getUser(userId);
        if (existingUser == null) {
            return "User does not exist!";
        }
        return userDAO.deleteUser(userId) ? "User deleted successfully!" : "Failed to delete user!";
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User getUserByContact(String contact) {
        return userDAO.getUserByContact(contact);
    }
}
