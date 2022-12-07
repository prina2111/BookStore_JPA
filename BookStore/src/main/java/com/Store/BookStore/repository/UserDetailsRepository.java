package com.Store.BookStore.repository;

import com.Store.BookStore.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {

    UserDetails findByUsername(String username);

    UserDetails findByEmail(String email);

    UserDetails findByMobileNumber(String mobileNumber);


}
