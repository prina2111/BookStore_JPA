package com.Store.BookStore.repository;

import com.Store.BookStore.model.BooksBorrowed;
import com.Store.BookStore.model.BorrowDetails;
import com.Store.BookStore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Modifying
    @Query(value = "insert into reviews (user_id,book_id,review) VALUES (?1,?2,?3)", nativeQuery = true)
    @Transactional
    void addReview(int userId, int bookId, String review);

    @Query(value = "select b from BooksBorrowed b where b.userId=?1 AND b.bookId=?2")
    List<BooksBorrowed> checkIfBookIsRented(int userId, int bookId);

    List<BooksBorrowed> findAllByBookId(int bookId);



}
