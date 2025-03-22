package com.controller;

import java.util.List;
import java.util.Scanner;

import com.dao.BorrowedBooksDAOImpl;
import com.dao.UserDAOImpl;
import com.entity.Book;
import com.entity.BorrowedBooks;
import com.service.BorrowedBooksService;
import com.service.BorrowedBooksServiceImpl;
import com.util.Layouts;

public class BorrowedBooksController {
    private BorrowedBooksService bs = new BorrowedBooksServiceImpl(new BorrowedBooksDAOImpl(), new UserDAOImpl());
    Scanner sc = new Scanner(System.in);

    public void borrowBook() {
        int userId;
        System.err.println("\nEnter your user Id: ");
        if (sc.hasNextInt()) {
            userId = sc.nextInt();
            sc.nextLine();
            if (userId <= 0) {
                System.out.println("\nEnter valid user Id!\n");
                return;
            }
        }
        else {
            System.out.println("\nEnter valid numeric value for user Id!\n");
            return;
        }
        System.err.println("\nEnter the book barcode: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("\nBarcode cannot be empty!\n");
            return;
        }
        String message = bs.borrowBook(code, userId);
        Layouts.printBold(message);
    };

    public void returnBook() {
        int borrowId;
        System.err.println("\nEnter your borrow Id: ");
        if (sc.hasNextInt()) {
            borrowId = sc.nextInt();
            sc.nextLine();
            if (borrowId <= 0) {
                System.out.println("\nEnter valid borrow Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for borrow Id!\n");
            return;
        }
        String message = bs.returnBook(borrowId);
        Layouts.printBold(message);
    };

    public void renewBook() {
        int borrowId;
        int userId;
        System.err.println("\nEnter your borrow Id: ");
        if (sc.hasNextInt()) {
            borrowId = sc.nextInt();
            sc.nextLine();
            if (borrowId <= 0) {
                System.out.println("\nEnter valid borrow Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for borrow Id!\n");
            return;
        }
        System.err.println("\nEnter your user Id: ");
        if (sc.hasNextInt()) {
            userId = sc.nextInt();
            sc.nextLine();
            if (userId <= 0) {
                System.out.println("\nEnter valid user Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for user Id!\n");
            return;
        }
        String message = bs.renewBook(borrowId, userId);
        Layouts.printBold(message);
    };

    public void isBookAlreadyBorrowed() {
        int userId;
        System.err.println("\nEnter your user Id: ");
        if (sc.hasNextInt()) {
            userId = sc.nextInt();
            sc.nextLine();
            if (userId <= 0) {
                System.out.println("\nEnter valid user Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for user Id!\n");
            return;
        }
        System.err.println("\nEnter the book barcode: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("\nBarcode cannot be empty!\n");
            return;
        }
        String message = bs.isBookBorrowed(code, userId);
        Layouts.printBold(message);
    };

    public void getBorrowedBooksByUser() {
        int userId;
        System.err.println("\nEnter your user Id: ");
        if (sc.hasNextInt()) {
            userId = sc.nextInt();
            sc.nextLine();
            if (userId <= 0) {
                System.out.println("\nEnter valid user Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for user Id!\n");
            return;
        }
        List<Book> books = bs.getBorrowedBooksByUser(userId);
        for (Book book : books) {
            Layouts.printBookData(book);
        }
    }

    public void getBorrowedBookDetail() {
        int borrowId;
        System.err.println("\nEnter your borrow Id: ");
        if (sc.hasNextInt()) {
            borrowId = sc.nextInt();
            sc.nextLine();
            if (borrowId <= 0) {
                System.out.println("\nEnter valid borrow Id!\n");
                return;
            }
        } else {
            System.out.println("\nEnter valid numeric value for borrow Id!\n");
            return;
        }
        BorrowedBooks borrowDetails = bs.getBorrowedBookDetail(borrowId);
        Layouts.printBorrowedBookData(borrowDetails);
    };

    public void getAllBorrowedBooksDetails() {
        List<BorrowedBooks> borrows = bs.getAllBorrowedBooksDetails();
        if (borrows.isEmpty()) {
            System.out.println("\nFound no data to display!\n");
            return;
        }
        for (BorrowedBooks borrow : borrows) {
            Layouts.printBorrowedBookData(borrow);
        }
    };
}
