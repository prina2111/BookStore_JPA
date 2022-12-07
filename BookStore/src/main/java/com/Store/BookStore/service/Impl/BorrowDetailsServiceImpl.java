package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.*;
import com.Store.BookStore.repository.*;

import com.Store.BookStore.service.BorrowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowDetailsServiceImpl implements BorrowDetailsService {

    @Autowired
    private BorrowDetailsRepository borrowDetailsRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @Autowired
    private BookInventoryRepository bookInventoryRepository;

    public boolean isUserEligibleToBorrowBook(UserDetails user, int bookId, int price, UserStats stats){

        int balance = stats.getBalance();
        int borrowCount = stats.getQuantity();

        int deposit = (20 * price)/100;

        List<BorrowDetails> borrowRecords = this.borrowDetailsRepository.checkBorrowCount(user.getUserID(), "rented");

        if(user.getIsSuspended() == 1 ||
                balance < deposit ||
                borrowCount == 3  ||
                (borrowCount != 0 && borrowRecords.get(borrowCount-1).getBookId() == bookId)
        )
            return false;

        return true;
    }

    @Override
    public void borrowBook(int bookId, int userId){
        Optional<Book> bookObj = this.bookRepository.findById(bookId);
//        Optional<BookInventory> bookInventoryObj = Optional.ofNullable(this.bookInventoryRepository.findByBookId(bookId));


        if(bookObj.isPresent()) {
            Book book = bookObj.get();
//            BookInventory bookInventory =  bookInventoryObj.get();

            Optional<UserDetails> userObj = this.userDetailsRepository.findById(userId);
            UserDetails user = userObj.get();
            Optional<UserStats> statsObj = this.userStatsRepository.findById(user.getUserstats().getStats_id());
//            Optional<UserStats> statsObj = this.userStatsRepository.findById(userId);

            UserStats stats = statsObj.get();
            boolean userEligibleToBorrowBook = isUserEligibleToBorrowBook(user, bookId, book.getPrice(), stats);
            int securityDeposit = (20*book.getPrice())/100;
            int amount = (10*book.getPrice())/100;
            if(book.getQuantity() != 0 && userEligibleToBorrowBook){
                String bookCode = borrowDetailsRepository.getBook(book.getBookId()).get(0).getBookCode();
                String bookName = borrowDetailsRepository.getBook(book.getBookId()).get(0).getBookName();
                borrowDetailsRepository.borrowBook(user.getUserID(),user.getUsername(),user.getEmail(),user.getMobileNumber(),book.getBookId(),bookCode,book.getBookName(),securityDeposit,amount,"rented");
                borrowDetailsRepository.updateBookQuantity(bookId,1);
                userStatsRepository.updateUserBalance(stats.getStats_id(),securityDeposit,1);
                booksBorrowedRepository.borrowBook(bookCode,userId,book.getBookId(),bookName);
                borrowDetailsRepository.deleteBook(bookCode);
                borrowDetailsRepository.addWalletTransaction(user.getUserID(),securityDeposit,0);
            }
        }
        else
            throw new UserDetailsException("Book is currently not available");
    }

    @Override
    public void returnBook(String bookCode, int userId){
        Optional<BorrowDetails> borrowRecordObj = Optional.ofNullable(this.borrowDetailsRepository.checkBorrowDetailsByBookCodeAndBorrowStatus(bookCode,"rented"));

        if(borrowRecordObj.isPresent()) {
            BorrowDetails borrowRecord = borrowRecordObj.get();
            int returnBalance = borrowRecord.getSecurityDeposit() - borrowRecord.getAmount();
            Optional<UserDetails> userObj = this.userDetailsRepository.findById(userId);
            UserDetails user = userObj.get();
            Optional<UserStats> statsObj = this.userStatsRepository.findById(user.getUserstats().getStats_id());

//            Optional<UserStats> statsObj = this.userStatsRepository.findById(userId);
            UserStats stats = statsObj.get();
            borrowDetailsRepository.borrowBook(borrowRecord.getUserId(),borrowRecord.getUsername(),borrowRecord.getEmail(),borrowRecord.getMobileNumber(),borrowRecord.getBookId(),borrowRecord.getBookCode(),borrowRecord.getBookName(),0,borrowRecord.getAmount(),"returned");
            borrowDetailsRepository.returnBook(borrowRecord.getBookId(),borrowRecord.getBookCode(),borrowRecord.getBookName());
            userStatsRepository.updateUserBalance(stats.getStats_id(),-returnBalance,-1);
            booksBorrowedRepository.returnBook(bookCode);
            booksBorrowedRepository.findById(bookCode);

            borrowDetailsRepository.updateBookQuantity(borrowRecord.getBookId(),-1);
            borrowDetailsRepository.addWalletTransaction(userId,0,returnBalance);
//            borrowDetailsRepository.updateBorrowStatus("returned",borrowRecord.getBookCode());
        }
        else
            throw new UserDetailsException("Book has not been borrowed");
    }

    @Override
    public List<BorrowDetails> getBorrowHistory(){
//        return this.borrowDetailsRepository.getBorrowHistory();
        return this.borrowDetailsRepository.findAll();

    }

    @Override
    public List<BorrowDetails> getUserBorrowHistory(int userId){
        return this.borrowDetailsRepository.getUserBorrowHistory(userId);


    }

}