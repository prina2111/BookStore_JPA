package com.Store.BookStore.service.Impl;

import com.Store.BookStore.model.WalletHistory;
import com.Store.BookStore.repository.WalletHistoryRepository;
import com.Store.BookStore.service.WalletHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WalletHistoryServiceImpl implements WalletHistoryService {

    @Autowired
    private WalletHistoryRepository walletHistoryRepository;

    public List<WalletHistory> getUserWalletTransactionHistory(int userId){
        return walletHistoryRepository.findByUserId(userId);
    }

}
