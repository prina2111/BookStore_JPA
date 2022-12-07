package com.Store.BookStore.service;

import com.Store.BookStore.model.UserStats;

public interface UserStatsService {
    UserStats addBalance(int userId, int balance);

}
