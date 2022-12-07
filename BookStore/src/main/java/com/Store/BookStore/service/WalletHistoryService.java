package com.Store.BookStore.service;

import com.Store.BookStore.model.WalletHistory;

import java.util.List;

public interface WalletHistoryService {
    List<WalletHistory> getUserWalletTransactionHistory(int userId);

}
