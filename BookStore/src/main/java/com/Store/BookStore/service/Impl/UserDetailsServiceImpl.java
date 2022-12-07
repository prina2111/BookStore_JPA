package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.repository.UserDetailsRepository;
import com.Store.BookStore.repository.UserStatsRepository;
import com.Store.BookStore.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Override
    public UserDetails createUser(UserDetails user){

        Optional<UserDetails> username = Optional.ofNullable(this.userDetailsRepository.findByUsername(user.getUsername()));
        Optional<UserDetails> email = Optional.ofNullable(this.userDetailsRepository.findByEmail(user.getEmail()));
        Optional<UserDetails> phone = Optional.ofNullable(this.userDetailsRepository.findByMobileNumber(user.getMobileNumber()));

        if(username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new UserDetailsException("User already exists");
        } else{
            UserDetails userDetails = userDetailsRepository.save(user);
//            userStatsRepository.addBalance(userDetails.getUserID(), 0,0);
            return userDetails;
        }

    }

    @Override
    public UserDetails updateUser(UserDetails user){
        Optional<UserDetails> userObj = this.userDetailsRepository.findById(user.getUserID());

        if(userObj.isPresent()) {
            UserDetails userUpdate = userObj.get();
            userUpdate.setMobileNumber(user.getMobileNumber());
            userUpdate.setIsSuspended(user.getIsSuspended());
            return this.userDetailsRepository.save(userUpdate);
        } else{
            throw new UserDetailsException("User not found with ID: " + user.getUserID());
        }

    }

    @Override
    public UserDetails updateSuspensionStatus(int userId){
        Optional<UserDetails> userObj = this.userDetailsRepository.findById(userId);

        if(userObj.isPresent()) {
            UserDetails userUpdate = userObj.get();
            userUpdate.setIsSuspended(1);
            return this.userDetailsRepository.save(userUpdate);
        } else{
            throw new UserDetailsException("User not found with ID: " + userId);
        }

    }

    @Override
    public List<UserDetails> getUsers(){
        return this.userDetailsRepository.findAll();
    }

    @Override
    public UserDetails getUserById(int userId){
        Optional<UserDetails> userObj = this.userDetailsRepository.findById(userId);

        if(userObj.isPresent()) {
            return userObj.get();
        } else{
            throw new UserDetailsException("User not found with ID: " + userId);
        }

    }

    @Override
    public void deleteUser(int userId){
        Optional<UserDetails> userObj = this.userDetailsRepository.findById( userId);

        if(userObj.isPresent()) {
            this.userDetailsRepository.deleteById((int) userId);
        } else{
            throw new UserDetailsException("User not found with ID: " + userId);
        }

    }

}