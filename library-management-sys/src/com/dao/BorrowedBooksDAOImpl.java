package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.config.DBConnection;
import com.entity.Book;
import com.entity.BorrowedBooks;
import com.util.Mappers;

public class BorrowedBooksDAOImpl implements BorrowedBooksDAO {

    @Override
    public boolean borrowBook(String bookCode, int userId) {
        String storedProcedure = "{CALL borrow_book(?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
                CallableStatement smt = conn.prepareCall(storedProcedure)) {
            smt.setString(1, bookCode);
            smt.setInt(2, userId);
            smt.registerOutParameter(3, Types.BOOLEAN);
            smt.execute();
            return smt.getBoolean(3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean returnBook(int borrowId) {
        String storedProcedure = "{CALL return_book(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
                CallableStatement smt = conn.prepareCall(storedProcedure)) {
            smt.setInt(1, borrowId);
            smt.registerOutParameter(2, Types.BOOLEAN);
            smt.execute();
            return smt.getBoolean(2);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean renewBook(int borrowId, int userId) {
        String storedProcedure = "{CALL renew_book(?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
                CallableStatement smt = conn.prepareCall(storedProcedure)) {
            smt.setInt(1, borrowId);
            smt.setInt(2, userId);
            smt.registerOutParameter(3, Types.BOOLEAN);
            smt.execute();
            return smt.getBoolean(3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isBookBorrowed(String bookCode, int userId) {
        String query = "SELECT COUNT(*) FROM borrowed_books bb JOIN books b ON bb.book_id = b.book_id WHERE b.isbn = ? AND bb.user_id = ? AND bb.return_date > NOW()";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookCode);
            stmt.setInt(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getBorrowedBooksByUser(int userId) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT b.book_id, b.title, b.author, b.isbn, b.available_copies FROM books b JOIN borrowed_books bb ON b.book_id = bb.book_id WHERE bb.user_id = ? AND bb.return_date > NOW()";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("book_id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setAvailableCopies(rs.getInt("available_copies"));
                    books.add(book);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public BorrowedBooks getBorrowedBookDetail(int borrow_id) {
        BorrowedBooks bb = null;
        String query = "SELECT bb.borrow_id, bb.borrow_date, bb.return_date, "
                + "b.book_id, b.title, b.author, b.isbn, b.available_copies, "
                + "u.user_id, u.first_name, u.last_name, u.gender, u.email, u.contact, u.city, u.state "
                + "FROM borrowed_books bb " + "JOIN books b ON bb.book_id = b.book_id "
                + "JOIN users u ON bb.user_id = u.user_id " + "WHERE bb.borrow_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, borrow_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bb = Mappers.mapResultingBorrowedBookDetail(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bb;
    }

    @Override
    public List<BorrowedBooks> getAllBorrowedBooksDetails() {
        List<BorrowedBooks> list = new ArrayList<>();
        String query = "SELECT bb.borrow_id, bb.borrow_date, bb.return_date, "
                + "b.book_id, b.title, b.author, b.isbn, b.available_copies, "
                + "u.user_id, u.first_name, u.last_name, u.gender, u.email, u.contact, u.city, u.state "
                + "FROM borrowed_books bb " + "JOIN books b ON bb.book_id = b.book_id "
                + "JOIN users u ON bb.user_id = u.user_id ";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                BorrowedBooks bb = Mappers.mapResultingBorrowedBookDetail(rs);
                list.add(bb);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
