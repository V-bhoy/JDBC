package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.DBConnection;
import com.entity.User;
import com.enums.Gender;
import com.util.Mappers;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO users (first_name, last_name, gender, city, state, contact, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getGender().toString());
            stmt.setString(4, user.getCity());
            stmt.setString(5, user.getState());
            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        StringBuilder query = new StringBuilder("UPDATE users SET ");
        List<Object> values = new ArrayList<>();

        if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
            query.append("first_name = ?, ");
            values.add(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().isEmpty()) {
            query.append("last_name = ?, ");
            values.add(user.getLastName());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            query.append("email = ?, ");
            values.add(user.getEmail());
        }
        if (user.getContact() != null && !user.getContact().isEmpty()) {
            query.append("contact = ?, ");
            values.add(user.getContact());
        }
        if (user.getGender() != null) {
            query.append("gender = ?, ");
            values.add(user.getGender().toString());
        }
        if (user.getCity() != null && !user.getCity().isEmpty()) {
            query.append("city = ?, ");
            values.add(user.getCity());
        }
        if (user.getState() != null && !user.getState().isEmpty()) {
            query.append("state = ?, ");
            values.add(user.getState());
        }
        if (values.isEmpty()) {
            return false;
        }

        query.setLength(query.length() - 2); // Remove last comma
        query.append(" WHERE user_id = ?");
        values.add(user.getUserId());
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                smt.setObject(i + 1, values.get(i));
            }
            System.out.println(query.toString());
            return smt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setInt(1, userId);
            return smt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUser(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setInt(1, userId);
            try (ResultSet userList = smt.executeQuery()) {
                user = Mappers.mapResultingUser(userList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * from users";
        List<User> userList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                Statement smt = conn.createStatement();
                ResultSet users = smt.executeQuery(query)) {

            while (users.next()) {
                User user = new User();
                user.setUserId(users.getInt("user_id"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setCity(users.getString("city"));
                user.setState(users.getString("state"));
                user.setGender(Gender.valueOf(users.getString("gender")));
                user.setContact(users.getString("contact"));
                user.setEmail(users.getString("email"));
                userList.add(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setString(1, email);
            try (ResultSet userList = smt.executeQuery()) {
                user = Mappers.mapResultingUser(userList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByContact(String contact) {
        String query = "SELECT * FROM users WHERE contact = ?";
        User user = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setString(1, contact);
            try (ResultSet userList = smt.executeQuery()) {
                user = Mappers.mapResultingUser(userList);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
