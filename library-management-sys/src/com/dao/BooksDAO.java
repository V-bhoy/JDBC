package com.dao;

import java.util.List;

import com.entity.Book;

public interface BooksDAO {
    public boolean registerBooks(List<Book> books);

    public boolean updateBook(Book book);

    public boolean deleteBook(String bookCode);

    public Book getBook(String bookCode);

    public List<Book> getBooksByAuthor(String author);

    public List<Book> getBooksByTitle(String title);

    public List<Book> getAllBooks();
}
