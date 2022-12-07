package com.Store.BookStore.model;

import javax.persistence.*;

@Entity
@Table(name = "books_borrowed")
public class BooksBorrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String book_code;

    @Column(name="user_id")
    private int userId;

    @Column(name="book_id")
    private int bookId;

    @Column(name="book_name")
    private String bookName;

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String  book_code) {
        this.book_code = book_code;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
