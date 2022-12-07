package com.Store.BookStore.controller;

import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.model.UserStats;
import com.Store.BookStore.service.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserStatsController {

    @Autowired
    private UserStatsService userStatsService;

    @PutMapping("/addBalance/{userId}")
    private ResponseEntity<UserStats> addBalance(@PathVariable int userId, @RequestParam() int balance){
        return ResponseEntity.ok().body(this.userStatsService.addBalance(userId,balance));
    }

}
