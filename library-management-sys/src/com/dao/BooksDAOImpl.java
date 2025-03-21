package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.DBConnection;
import com.entity.Book;
import com.util.Mappers;

public class BooksDAOImpl implements BooksDAO{
    @Override
    public boolean registerBooks(List<Book> books) {
        if (books.isEmpty()) {
            return false;
        }
        Connection conn = null;
        String query = "INSERT INTO books (title, author, isbn, available_copies) VALUES (?, ?, ?, ?)";
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            try (PreparedStatement smt = conn.prepareStatement(query)) {
                for (Book book : books) {
                    smt.setString(1, book.getTitle());
                    smt.setString(2, book.getAuthor());
                    smt.setString(3, book.getIsbn());
                    smt.setInt(4, book.getAvailableCopies());
                    smt.addBatch();
                }
                smt.executeBatch();
                conn.commit();
                return true;
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackErr) {
                    rollbackErr.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeConnectionErr) {
                    closeConnectionErr.printStackTrace();
                } 
            }
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        StringBuilder query = new StringBuilder("UPDATE books SET ");
        List<Object> values = new ArrayList<>();
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            query.append("author = ?, ");
            values.add(book.getAuthor());
        }
        if (book.getTitle() != null && !book.getTitle().isEmpty()) {
            query.append("title = ?, ");
            values.add(book.getTitle());
        }
        if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
            query.append("isbn = ?, ");
            values.add(book.getIsbn());
        }
        if (book.getAvailableCopies() > 0) {
            query.append("available_copies = ?, ");
            values.add(book.getAvailableCopies());
        }
        if (values.isEmpty()) {
            return false;
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE book_id = ?");
        values.add(book.getBookId());
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement smt = conn.prepareStatement(query.toString())
        ){
            for (int i = 0; i < values.size(); i++) {
                smt.setObject(i + 1, values.get(i));
            }
            return smt.executeUpdate() > 0;
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setInt(1, bookId);
            return smt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book getBook(String bookCode) {
        Book book = null;
        String query = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.setString(1, bookCode);
            try (ResultSet res = smt.executeQuery()) {
                book = Mappers.mapResultingBook(res);
                return book;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE author like ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement smt = conn.prepareStatement(query);
        ) {
            smt.setString(1, "%"+author+"%");
            try(ResultSet res = smt.executeQuery()){
                books = Mappers.mapResultingBooks(res);
            }   
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title like ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement smt = conn.prepareStatement(query);) {
            smt.setString(1, "%" + title + "%");
            try (ResultSet res = smt.executeQuery()) {
                books = Mappers.mapResultingBooks(res);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection(); 
                Statement smt = conn.createStatement();
                ResultSet res = smt.executeQuery(query)) {
            books = Mappers.mapResultingBooks(res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}
