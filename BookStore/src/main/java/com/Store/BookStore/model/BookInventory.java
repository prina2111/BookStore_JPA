package com.Store.BookStore.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_inventory")

public class BookInventory {

//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="book_id")
    private int bookId;

    @Id
    @Column(name="book_code")
    private String bookCode;

    @Column(name="book_name")
    private String bookName;

    @CreationTimestamp
    @Column(name="created_time")
    private Date createdTime;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}

