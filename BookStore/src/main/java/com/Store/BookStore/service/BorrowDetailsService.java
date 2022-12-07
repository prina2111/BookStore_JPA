package com.Store.BookStore.service;

import com.Store.BookStore.model.BorrowDetails;

import java.util.List;

public interface BorrowDetailsService {

    void borrowBook(int bookId, int userId);

    void returnBook(String bookCode, int userId);

    List<BorrowDetails> getUserBorrowHistory(int userId);

    List<BorrowDetails> getBorrowHistory();

}