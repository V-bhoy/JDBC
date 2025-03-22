package com.service;

import java.util.List;

import com.entity.Book;
import com.entity.BorrowedBooks;

public interface BorrowedBooksService {
    public String borrowBook(String bookCode, int userId);

    public String returnBook(int borrowId);

    public String renewBook(int borrowId, int userId);

    public String isBookBorrowed(String bookCode, int userId);

    public List<Book> getBorrowedBooksByUser(int userId);

    public BorrowedBooks getBorrowedBookDetail(int borrow_id);

    public List<BorrowedBooks> getAllBorrowedBooksDetails();
}
