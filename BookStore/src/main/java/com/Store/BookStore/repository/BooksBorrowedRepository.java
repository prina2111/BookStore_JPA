package com.Store.BookStore.repository;

import com.Store.BookStore.model.BooksBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BooksBorrowedRepository extends JpaRepository<BooksBorrowed,String> {

    @Modifying
    @Query(value = "insert into books_borrowed (book_code, user_id, book_id, book_name) VALUES (?1,?2,?3,?4)", nativeQuery = true)
    @Transactional
    void borrowBook(String bookCode, int userId, int bookId, String bookName);

    @Modifying
    @Query(value = "delete from BooksBorrowed where book_code =?1")
    @Transactional
    void returnBook(String bookCode);


}
