package com.service;

import java.util.List;

import com.entity.User;

public interface UserService {
    public String addUser(User user);
    
    public String updateUser(User user);

    public String deleteUser(int userId);

    public User getUser(int userId);

    public User getUserByEmail(String email);

    public User getUserByContact(String contact);

    public List<User> getAllUsers();
    
}
