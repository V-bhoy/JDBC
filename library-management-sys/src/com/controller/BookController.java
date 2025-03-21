package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dao.BooksDAOImpl;
import com.entity.Book;
import com.service.BookService;
import com.service.BookServiceImpl;
import com.util.Layouts;

public class BookController {
    private BookService bs = new BookServiceImpl(new BooksDAOImpl());
    Scanner sc = new Scanner(System.in);

    public void registerBooks() {
        List<Book> books = new ArrayList<>();
        Set<String> codes = new HashSet<>();
        System.out.println("\nEnter the number of books to register: \n");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();
            if (n <= 0) {
                System.out.println("\nEnter a number greater than 0!\n");
                return;
            }
            for (int i = 0; i < n; i++) {
                Book b = new Book();

                String title;
                do {
                    System.out.println("\nEnter the title of the book: ");
                    title = sc.nextLine().trim();
                    if (title.isEmpty()) {
                        System.out.println("\nTitle cannot be empty!\n");
                    }
                } while (title.isEmpty());
                b.setTitle(title);

                String author;
                do {
                    System.out.println("\nEnter the author of the book: ");
                    author = sc.nextLine().trim();
                    if (author.isEmpty()) {
                        System.out.println("\nAuthor cannot be empty!\n");
                    }
                } while (author.isEmpty());
                b.setAuthor(author);

                String barcode;
                do {
                    System.out.println("\nEnter the barcode of the book, it cannot be updated later: ");
                    barcode = sc.nextLine().trim();
                    if (barcode.isEmpty()) {
                        System.out.println("\nBarcode cannot be empty!\n");
                    } else if (codes.contains(barcode)) {
                        System.out.println("\nThe barcode must be unique for every book!\n");
                        barcode = ""; // Force loop to repeat
                    }
                } while (barcode.isEmpty());
                b.setIsbn(barcode);
                codes.add(barcode);

                int copies;
                while (true) {
                    System.out.println("\nEnter the number of copies of the book: ");
                    if (sc.hasNextInt()) {
                        copies = sc.nextInt();
                        sc.nextLine(); // ✅ Consume newline after nextInt()
                        if (copies > 0)
                            break;
                    } else {
                        System.out.println("\nEnter a valid numeric value for copies!\n");
                        sc.next(); // ✅ Consume invalid input
                    }
                }
                b.setAvailableCopies(copies);

                books.add(b);
            }
            String message = bs.registerBooks(books);
            Layouts.printBold(message);
        } else {
            System.out.println("\nEnter valid numeric value!\n");
            sc.next();
        }
    }

    public void updateBook() {
        Book b = new Book();
        System.out.println("\nEnter the barcode of book to update: \n");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("\nBarcode cannot be empty!\n");
            return;
        }
        b.setIsbn(code);
        System.out.println("\nEnter the TITLE to update or press ENTER to skip: ");
        String title = sc.nextLine().trim();
        if (!title.isEmpty()) {
            b.setTitle(title);
        }
        System.out.println("\nEnter the AUTHOR to update or press ENTER to skip: ");
        String author = sc.nextLine().trim();
        if (!author.isEmpty()) {
            b.setAuthor(author);
        }
        System.out.println("\nEnter the COPIES to update or press ENTER to skip: ");
        int copies = 1;
        while (true) {
            if (sc.hasNextInt()) {
                copies = sc.nextInt();
                sc.nextLine();
                if (copies > 0) {
                    break;
                }
            } else {
                System.out.println("Please enter a valid numeric value");
                sc.next();
            }
        }
        b.setAvailableCopies(copies);
        String message = bs.updateBook(b);
        Layouts.printBold(message);
    }

    public void deleteBook() {
        System.out.println("\nEnter the barcode to delete: \n");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("\nBarcode cannot be empty!\n");
            return;
        }
        String message = bs.deleteBook(code);
        Layouts.printBold(message);
    }

    public void getBook(int choice) {
        if (choice == 4) {
            System.out.println("\nEnter the barcode to fetch details: \n");
            String code = sc.nextLine().trim();
            if (code.isEmpty()) {
                System.out.println("\nBarcode cannot be empty!\n");
                return;
            }
            Book book = bs.getBook(code);
            if (book == null) {
                System.out.println("\nFound no details for the book!\n");
            } else {
                Layouts.printBookData(book);
            }
        } else {
            List<Book> books;
            if (choice == 5) {
                System.out.println("\nEnter the author to fetch details: \n");
                String author = sc.nextLine().trim();
                if (author.isEmpty()) {
                    System.out.println("\nAuthor cannot be empty!\n");
                    return;
                }
                books = bs.getBooksByAuthor(author);

            } else {
                System.out.println("\nEnter the title to fetch details: \n");
                String title = sc.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("\nTitle cannot be empty!\n");
                    return;
                }
                books = bs.getBooksByTitle(title);
            }
            if (books.isEmpty()) {
                System.out.println("\nFound no books to display!\n");
            } else {
                for (Book book : books) {
                    Layouts.printBookData(book);
                }
            }
        }
    }

    public void getAllBooks() {
        List<Book> books = bs.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("\nFound no books to display!\n");
        } else {
            for (Book book : books) {
                Layouts.printBookData(book);
            }
        }
    }
}
