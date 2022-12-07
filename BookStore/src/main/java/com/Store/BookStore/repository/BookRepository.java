package com.Store.BookStore.repository;

import com.Store.BookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findByBookName(String bookName);

    @Query(value = "SELECT CASE WHEN b.quantity=0 THEN CONCAT(b.book_name,\" is currently out of stock\") ELSE CONCAT(b.book_name,\" is available\") END FROM books b", nativeQuery = true)
    List<String> checkAvailableBooks();


}

