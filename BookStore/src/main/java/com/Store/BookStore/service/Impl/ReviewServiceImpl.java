package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.BooksBorrowed;
import com.Store.BookStore.repository.ReviewRepository;
import com.Store.BookStore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(int userId, int bookId, String review){

        List<BooksBorrowed> l = reviewRepository.checkIfBookIsRented(userId,bookId);
         if(l.size() != 0)
             this.reviewRepository.addReview(userId,bookId,review);
         else
             throw new UserDetailsException("Book is not rented");
    }
}
