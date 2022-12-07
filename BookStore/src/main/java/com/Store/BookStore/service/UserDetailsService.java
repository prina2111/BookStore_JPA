package com.Store.BookStore.service;

import com.Store.BookStore.model.UserDetails;

import java.util.List;

public interface UserDetailsService {

    UserDetails createUser(UserDetails user);

    UserDetails updateUser(UserDetails user);

    UserDetails updateSuspensionStatus(int userId);

    List<UserDetails> getUsers();

    UserDetails getUserById(int userId);

    void deleteUser(int userId);
}