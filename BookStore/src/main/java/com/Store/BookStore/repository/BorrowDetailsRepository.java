package com.Store.BookStore.repository;

import com.Store.BookStore.model.Book;
import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.model.BorrowDetails;
import com.Store.BookStore.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BorrowDetailsRepository extends JpaRepository<BorrowDetails,Integer> {

    @Query(value = "select * from Borrow_Details where book_code=?1 AND borrow_status=?2 order by borrow_date limit 1",nativeQuery = true)
    BorrowDetails checkBorrowDetailsByBookCodeAndBorrowStatus(String bookCode, String borrowStatus);
    @Query(value = "select b from BorrowDetails b where b.userId=?1 AND b.borrowStatus=?2 ")
    List<BorrowDetails> checkBorrowCount(int userId, String borrowStatus);

    @Modifying
    @Query(value = "insert into borrow_details (user_id,username,email,mobile_number,book_id,book_code,book_name,security_deposit,amount,borrow_status) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    @Transactional
    void borrowBook(int userId, String username, String email, String mobileNumber, int bookId, String bookCode, String bookName, int securityDeposit, int amount, String borrowStatus);

    @Modifying
    @Query(value = "insert into book_inventory (book_id,book_code,book_name) VALUES (?1,?2,?3)", nativeQuery = true)
    @Transactional
    void returnBook(int bookId, String bookCode, String bookName);

    @Modifying
    @Query(value = "insert into wallet_transaction (user_id,balance_debited,balance_credited) VALUES (?1,?2,?3)", nativeQuery = true)
    @Transactional
    void addWalletTransaction(int userId, int balance_debited, int balance_credited);
    @Query(value = "select b from BookInventory b where b.bookId=?1")
    List<BookInventory> getBook(int bookId);

    @Query(value = "select b from BorrowDetails b where b.userId=?1")
    List<BorrowDetails> getUserBorrowHistory(int userId);


    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.quantity = b.quantity - ?2 where b.bookId =?1")
    void updateBookQuantity(int bookId, int q);


    @Modifying(clearAutomatically = true)
    @Query("update BorrowDetails b set b.borrowStatus =?1 where b.bookCode =?2")
    void updateBorrowStatus(String borrowStatus, String bookCode);

    @Modifying
    @Query("delete from BookInventory b where b.bookCode = ?1")
    void deleteBook(String bookCodee);



}