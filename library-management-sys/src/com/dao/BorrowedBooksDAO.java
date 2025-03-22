package com.dao;

import java.util.List;

import com.entity.Book;
import com.entity.BorrowedBooks;

public interface BorrowedBooksDAO {
    public boolean borrowBook(String bookCode, int userId);

    public boolean returnBook(int borrowId);

    public boolean renewBook(int borrowId, int userId);

    public boolean isBookBorrowed(String bookCode, int userId);

    public List<Book> getBorrowedBooksByUser(int userId);

    public BorrowedBooks getBorrowedBookDetail(int borrow_id);

    public List<BorrowedBooks> getAllBorrowedBooksDetails();
   
}
