package com.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.User;
import com.enums.Gender;

public class Mappers {
    public static User mapResultingUser(ResultSet rs) {
        try {
            if (rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setGender(Gender.valueOf(rs.getString("gender")));
                user.setContact(rs.getString("contact"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        }
        catch(SQLException e){
          e.printStackTrace();
        }
        return null;
   }
}
