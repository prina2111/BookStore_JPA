package com.Store.BookStore.repository;

import com.Store.BookStore.model.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserStatsRepository extends JpaRepository<UserStats,Integer> {
    @Modifying
    @Query("update UserStats u set u.balance = u.balance - ?2, u.quantity = u.quantity + ?3 where u.stats_id =?1")
    @Transactional
    void updateUserBalance(int statsId, int securityDeposit, int quantity);

}
