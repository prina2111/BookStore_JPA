package com.Store.BookStore.controller;

import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.service.BookInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookInventoryController {

    @Autowired
    private BookInventoryService bookInventoryService;

    @GetMapping("/inventoryBooks")
    private ResponseEntity<List<BookInventory>> getAllBooks(){
        return ResponseEntity.ok().body(this.bookInventoryService.getBooks());
    }

    @GetMapping("/inventoryBooks/{bookCode}")
    private BookInventory getBookById(@PathVariable String bookCode){
        return this.bookInventoryService.getBookByCode(bookCode);
    }

    @PostMapping("/inventoryBooks")
    private ResponseEntity<BookInventory> getBookById(@RequestBody BookInventory book){
        return ResponseEntity.ok().body(this.bookInventoryService.addBook(book));
    }

//    @DeleteMapping("/inventoryBooks/{bookCode}")
//    private HttpStatus deleteUser(@PathVariable String bookCode){
//        this.bookInventoryService.deleteBook(bookCode);
//        return HttpStatus.OK;
//    }

}
