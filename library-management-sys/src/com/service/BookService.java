package com.service;

import java.util.List;

import com.entity.Book;

public interface BookService {
    public String registerBooks(List<Book> books);
    
    public String updateBook(Book book);

    public String deleteBook(String bookCode);

    public Book getBook(String bookCode);

    public  List<Book> getBooksByAuthor(String author);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getAllBooks();
}
