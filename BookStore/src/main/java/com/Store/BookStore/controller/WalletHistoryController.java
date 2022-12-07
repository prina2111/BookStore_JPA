package com.Store.BookStore.controller;

import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.model.WalletHistory;
import com.Store.BookStore.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletHistoryController {

    @Autowired
    private WalletHistoryService walletHistoryService;

    @GetMapping("/walletTransactionHistory/{userId}")
    private ResponseEntity<List<WalletHistory>> getUserWalletTransactionHistory(@PathVariable int userId){
        return ResponseEntity.ok().body(this.walletHistoryService.getUserWalletTransactionHistory(userId));
    }

}