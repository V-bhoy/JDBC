package com.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Book;
import com.entity.User;
import com.enums.Gender;

public class Mappers {
    public static User mapResultingUser(ResultSet rs) throws SQLException {

        if (rs.next()) {
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

        return null;
    }

    public static Book mapResultingBook(ResultSet rs) throws SQLException {

        if (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setIsbn(rs.getString("isbn"));
            book.setAvailableCopies(rs.getInt("available_copies"));
            return book;
        }

        return null;
    }

    public static List<Book> mapResultingBooks(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setIsbn(rs.getString("isbn"));
            book.setAvailableCopies(rs.getInt("available_copies"));
            books.add(book);
        }

        return books;
    }
}
