package com.Store.BookStore.controller;

import com.Store.BookStore.model.Book;
import com.Store.BookStore.model.UserDetails;
import com.Store.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    private ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(this.bookService.getBooks());
    }

    @GetMapping("/books/{bookId}")
    private Book getBookById(@PathVariable int bookId){
        return this.bookService.getBookById(bookId);
    }

    @GetMapping("/availableBooks")
    private ResponseEntity<List<String>> getAvailableBooks(){
        return ResponseEntity.ok().body(this.bookService.getAvailableBooks());
    }

    @GetMapping("/sortByLikes")
    private ResponseEntity<List<Book>> sortByLikes(){
        return ResponseEntity.ok().body(this.bookService.sortByLikes());
    }

    @GetMapping("/sortByDate")
    private ResponseEntity<List<Book>> sortByDate(){
        return ResponseEntity.ok().body(this.bookService.sortByDate());
    }

    @PostMapping("/addBook")
    private ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.ok().body(this.bookService.addBook(book));
    }

    @PutMapping("/likeBook/{bookId}")
    private ResponseEntity<Book> likeBook(@PathVariable int bookId){
        return ResponseEntity.ok().body(this.bookService.updateLikes(bookId));
    }

}
