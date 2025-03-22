package com.controller;

import com.dao.BorrowedBooksDAOImpl;
import com.dao.UserDAOImpl;
import com.service.BorrowedBooksService;
import com.service.BorrowedBooksServiceImpl;

public class BorrowedBooksController {
    private BorrowedBooksService bs = new BorrowedBooksServiceImpl(new BorrowedBooksDAOImpl(), new UserDAOImpl());

    public void borrowBook(){};

    public void returnBook() {
    };

    public void renewBook() {
    };

    public void isBookAlreadyBorrowed() {
    };

    public void getBorrowedBooksByUser(){}

    public void getBorrowedBookDetail() {
    };

    public void getAllBorrowedBooksDetails() {
    };
}
