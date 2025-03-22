package com.entity;

public class BorrowedBooks {
    private int borrowId;
    private User user;
    private Book book;
    private String borrowDate;
    private String returnDate;
    private String actualReturnDate;

    public int getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public String getActualReturnDate() {
        return actualReturnDate;
    }
    public void setActualReturnDate(String actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    @Override
    public String toString() {
        return "BorrowedBooks [borrowId=" + borrowId + ", user=" + user + ", book=" + book + ", borrowDate="
                + borrowDate + ", returnDate=" + returnDate + ", actualReturnDate=" + actualReturnDate + "]";
    }
     
}
