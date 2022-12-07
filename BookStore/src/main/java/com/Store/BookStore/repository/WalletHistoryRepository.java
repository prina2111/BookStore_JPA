package com.Store.BookStore.repository;
import com.Store.BookStore.model.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletHistoryRepository extends JpaRepository<WalletHistory,Integer> {
    List<WalletHistory> findByUserId(int userId);

}
