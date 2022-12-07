package com.Store.BookStore.controller;

import com.Store.BookStore.model.Review;
import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/addReview/{bookId}/{userId}")
    private void addReview(@PathVariable int bookId, @PathVariable int userId, @RequestBody Review review){
        this.reviewService.addReview(userId,bookId,review.getReview());
    }
}
