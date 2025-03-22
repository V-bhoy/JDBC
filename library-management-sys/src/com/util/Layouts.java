package com.util;

import com.entity.Book;
import com.entity.BorrowedBooks;
import com.entity.User;

public class Layouts {

    public static void printBold(String message) {
        System.out.println("\n*********************************************\n");
        System.err.println(message);
        System.out.println("\n**********************************************\n");
    }

    public static void printMargin() {
        System.out.println("\n-----------------------------------------------------------------\n");
    }
    
    public static void printUserData(User user) {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
        System.out.println("USER ID: " + user.getUserId());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("GENDER: " + user.getGender());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("CONTACT: " + user.getContact());
        System.out.println("CITY: " + user.getCity());
        System.out.println("STATE: " + user.getState());
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
    }

    public static void printBookData(Book book) {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
        System.out.println("BOOK ID: " + book.getBookId());
        System.out.println("TITLE: " + book.getTitle());
        System.out.println("AUTHOR: " + book.getAuthor());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("AVAILABLE COPIES: " + book.getAvailableCopies());
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
    }

    public static void printBorrowedBookData(BorrowedBooks borrow) {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
        System.out.println("BORROW ID: " + borrow.getBorrowId());
        System.out.println("BORROW DATE: " + borrow.getBorrowDate());
        System.out.println("RETURN DATE: " + borrow.getReturnDate());
        System.out.println("ACTUAL RETURN DATE: " + borrow.getActualReturnDate());
        System.out.println("BORROWED BOOK DETAILS: ");
        printBookData(borrow.getBook());
        System.out.println("BORROWED BY USER DETAILS: ");
        printUserData(borrow.getUser());
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
    }
}
