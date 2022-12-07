package com.Store.BookStore.service;

import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.repository.BookRepository;

import java.util.List;

public interface BookInventoryService {

    BookInventory addBook(BookInventory book);

    List<BookInventory> getBooks();

    BookInventory getBookByCode(String bookCode);

}