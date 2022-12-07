package com.Store.BookStore.repository;

import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookInventoryRepository extends JpaRepository<BookInventory,String> {

}
