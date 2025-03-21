package com.controller;

import java.util.Scanner;

import com.dao.BooksDAOImpl;
import com.service.BookService;
import com.service.BookServiceImpl;

public class BookController {
    private BookService bs = new BookServiceImpl(new BooksDAOImpl());
    Scanner sc = new Scanner(System.in);

    public void registerBooks() {

    }
    
    public void updateBook() {

    }
    
    public void deleteBook() {

    }
    
    public void getBook(int choice) {

    }
    
    public void getAllBooks() {
        
    }
}
