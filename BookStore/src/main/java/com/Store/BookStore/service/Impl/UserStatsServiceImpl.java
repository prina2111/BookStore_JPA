package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.model.UserStats;
import com.Store.BookStore.repository.UserDetailsRepository;
import com.Store.BookStore.repository.UserStatsRepository;
import com.Store.BookStore.service.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserStatsServiceImpl implements UserStatsService {

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserStats addBalance(int userId, int balance){

        Optional<UserDetails> userObj = this.userDetailsRepository.findById(userId);
        UserDetails user = userObj.get();
        Optional<UserStats> statsObj = this.userStatsRepository.findById(user.getUserstats().getStats_id());
//        Optional<UserStats> statsObj = this.userStatsRepository.findById(userId);

        if(statsObj.isPresent()) {
            UserStats balanceUpdate = statsObj.get();
            if(balance%500 == 0){
                balanceUpdate.setBalance(balanceUpdate.getBalance() + balance);
                return this.userStatsRepository.save(balanceUpdate);
            } else{
                throw new UserDetailsException("Balance should be in multiples of 500");
            }

        } else{
            throw new UserDetailsException("User not found with ID: " + userId);
        }

    }

}
