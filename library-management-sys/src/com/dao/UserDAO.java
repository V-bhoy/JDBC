package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDAO {
    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(int userId);

    public User getUser(int userId);

    public User getUserByEmail(String email);

    public User getUserByContact(String contact);

    public List<User> getAllUsers();
}
