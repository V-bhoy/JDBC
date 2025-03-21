package com.service;

import java.util.List;

import com.dao.BooksDAO;
import com.entity.Book;

public class BookServiceImpl implements BookService {
    private BooksDAO booksDAO;

    public BookServiceImpl(BooksDAO dao) {
        this.booksDAO = dao;
    }
    @Override
    public String registerBooks(List<Book> books) {
        int existingBooks = 0;
        int booksToRegister = books.size();
        for (Book book : books) {
            Book existingBook = booksDAO.getBook(book.getIsbn());
            if (existingBook != null) {
                existingBooks++;
                books.remove(book);
            }
        }
        if (books.isEmpty() && existingBooks == booksToRegister) {
            return "The books are already registered!";
        }
        else {
            boolean isRegistered = booksDAO.registerBooks(books);
            if (isRegistered && existingBooks > 0) {
                return "Successfully registered " + books.size() + "books and found " + existingBooks
                        + " already registered!";
            }
            return isRegistered ? "Successfully registered all books!" : "Failed to register books!";
        }
    }

    @Override
    public String updateBook(Book book) {
        Book existingBook = booksDAO.getBook(book.getIsbn());
        if (existingBook == null) {
            return "Book does not exist! Please register the book first.";
        }
        return booksDAO.updateBook(book) ? "Updated book successfully!" : "Failed to update book!";
    }

    @Override
    public String deleteBook(String bookCode) {
        Book existingBook = booksDAO.getBook(bookCode);
        if (existingBook == null) {
            return "Book does not exist! Please register the book first.";
        }
        return booksDAO.deleteBook(bookCode) ? "Deleted book successfully!" : "Failed to delete book!";
    }

    @Override
    public Book getBook(String bookCode) {
        return booksDAO.getBook(bookCode);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return booksDAO.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return booksDAO.getBooksByTitle(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return getAllBooks();
    }

}
