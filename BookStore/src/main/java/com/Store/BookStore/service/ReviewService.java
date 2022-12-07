package com.Store.BookStore.service;

import com.Store.BookStore.model.Review;

public interface ReviewService {

    void addReview(int userId, int bookId, String review);

}
