package com.service;

import java.util.List;

import com.dao.BorrowedBooksDAO;
import com.dao.UserDAO;
import com.entity.Book;
import com.entity.BorrowedBooks;
import com.entity.User;

public class BorrowedBooksServiceImpl implements BorrowedBooksService{
    private BorrowedBooksDAO borrowedBooksDAO;
    private UserDAO userDAO;

    public BorrowedBooksServiceImpl(BorrowedBooksDAO dao, UserDAO userDao) {
        this.borrowedBooksDAO = dao;
        this.userDAO = userDao;
    }
    @Override
    public String borrowBook(String bookCode, int userId) {
        User userExists = userDAO.getUser(userId);
        if (userExists == null) {
            return "User does not exist!";
        }
        Boolean isBookAlreadyBorrowed = borrowedBooksDAO.isBookBorrowed(bookCode, userId);
        if (isBookAlreadyBorrowed) {
            return "The book is already borrowed!";
        }
        int borrowId = borrowedBooksDAO.borrowBook(bookCode, userId);
        return borrowId!=0 ? "SUCCESS! - Borrow Id - "+borrowId+"\nRemember this to track details!" : "FAILED - copies not available or the book does not exist!";
    }

    @Override
    public String returnBook(int borrowId) {
        return borrowedBooksDAO.returnBook(borrowId) ? "SUCCESS!" : "FAILED - the book was already returned with this borrow_id or corrupted data, the book id does not exist!";
    }

    @Override
    public String renewBook(int borrowId, int userId) {
        return borrowedBooksDAO.renewBook(borrowId, userId) ? "SUCCESS" : "FAILED - return date overdue or the book was already returned!";
    }

    @Override
    public String isBookBorrowed(String bookCode, int userId) {
        User userExists = userDAO.getUser(userId);
        if (userExists == null) {
            return "User does not exist!";
        }
        return borrowedBooksDAO.isBookBorrowed(bookCode, userId) ? "The book is borrowed!" : "The book is not borrowed or The book does not exist!";
    }

    @Override
    public List<Book> getBorrowedBooksByUser(int userId) {
        return borrowedBooksDAO.getBorrowedBooksByUser(userId); 
    }

    @Override
    public BorrowedBooks getBorrowedBookDetail(int borrow_id) {
        return borrowedBooksDAO.getBorrowedBookDetail(borrow_id);
    }

    @Override
    public List<BorrowedBooks> getAllBorrowedBooksDetails() {
        return borrowedBooksDAO.getAllBorrowedBooksDetails();
    }

}
