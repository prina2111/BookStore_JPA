package com.Store.BookStore.controller;

import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class UserDetailsController {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/users")
    private ResponseEntity<List<UserDetails>> getAllUsers(){
        log.info("In change-password ...");
        return ResponseEntity.ok().body(this.userDetailsService.getUsers());
    }

    @GetMapping("/users/{userId}")
    private UserDetails getUserById(@PathVariable int userId){
        return this.userDetailsService.getUserById(userId);
    }

    @PostMapping("/addUser")
    private ResponseEntity<UserDetails> getUserById(@RequestBody UserDetails user){
        return ResponseEntity.ok().body(this.userDetailsService.createUser(user));
    }

    @PutMapping("/users/{userId}")
    private ResponseEntity<UserDetails> updateUser(@PathVariable int userId, @RequestBody UserDetails user){
        user.setUserID(userId);
        return ResponseEntity.ok().body(this.userDetailsService.updateUser(user));
    }

    @PutMapping("/suspendUser/{userId}")
    private ResponseEntity<UserDetails> updateSuspensionStatus(@PathVariable int userId, @RequestBody UserDetails user){
        return ResponseEntity.ok().body(this.userDetailsService.updateSuspensionStatus(userId));
    }

//    @DeleteMapping("/users/{userId}")
//    private HttpStatus deleteUser(@PathVariable int userId){
//        this.userDetailsService.deleteUser(userId);
//        return HttpStatus.OK;
//    }

}