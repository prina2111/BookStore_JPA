package com.Store.BookStore.service;

import com.Store.BookStore.model.Book;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    Book updateLikes(int bookId);

    List<Book> getBooks();

    List<String> getAvailableBooks();

    Book getBookById(int bookId);

    List<Book> sortByLikes();

    List<Book> sortByDate();



}
