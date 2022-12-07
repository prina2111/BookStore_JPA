package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.Book;
import com.Store.BookStore.repository.BookRepository;
import com.Store.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book){

        Optional<Book> bookObj = Optional.ofNullable(this.bookRepository.findByBookName(book.getBookName()));

        if(bookObj.isPresent()) {
            Book bookUpdate = bookObj.get();
            bookUpdate.setQuantity(bookUpdate.getQuantity() + 1);
            return bookRepository.save(bookUpdate);
        } else{
            return bookRepository.save(book);
        }

    }

    @Override
    public Book updateLikes(int bookId){
        Optional<Book> userObj = this.bookRepository.findById(bookId);

        if(userObj.isPresent()) {
            Book userUpdate = userObj.get();
            userUpdate.setLikes(userUpdate.getLikes() + 1);
            return this.bookRepository.save(userUpdate);
        } else{
            throw new UserDetailsException("Book not found with ID: " + bookId);
        }

    }

    @Override
    public List<Book> getBooks(){
        return this.bookRepository.findAll();
    }

    @Override
    public List<String> getAvailableBooks(){
        return this.bookRepository.checkAvailableBooks();
    }

    @Override
    public Book getBookById(int bookId){
        Optional<Book> userObj = this.bookRepository.findById(bookId);

        if(userObj.isPresent()) {
            return userObj.get();
        } else{
            throw new UserDetailsException("Book not found with ID: " + bookId);
        }

    }

    @Override
    public List<Book> sortByLikes() {
        return this.bookRepository.findAll(Sort.by("likes").descending());
    }

    @Override
    public List<Book> sortByDate() {
        return this.bookRepository.findAll(Sort.by("createdTime"));
    }

}
