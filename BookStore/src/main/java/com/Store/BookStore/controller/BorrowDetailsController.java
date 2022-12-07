package com.Store.BookStore.controller;

import com.Store.BookStore.model.BorrowDetails;
import com.Store.BookStore.service.BorrowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowDetailsController {

    @Autowired
    private BorrowDetailsService borrowDetailsService;

    @GetMapping("/borrowHistory/{userId}")
    private ResponseEntity<List<BorrowDetails>> getUserBorrowHistory(@PathVariable int userId){
        return ResponseEntity.ok().body(this.borrowDetailsService.getUserBorrowHistory(userId));
    }

    @GetMapping("/borrowHistory")
    private ResponseEntity<List<BorrowDetails>> getBorrowHistory(){
        return ResponseEntity.ok().body(this.borrowDetailsService.getBorrowHistory());
    }

    @PostMapping("/borrowBook/{bookId}/{userId}")
    private void borrowBook(@PathVariable int bookId, @PathVariable int userId){
        this.borrowDetailsService.borrowBook(bookId,userId);
    }

    @PostMapping("/returnBook/{bookCode}/{userId}")
    private void returnBook(@PathVariable String bookCode, @PathVariable int userId){
        this.borrowDetailsService.returnBook(bookCode,userId);
    }


}

