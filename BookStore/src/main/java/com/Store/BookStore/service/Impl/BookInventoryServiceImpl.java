package com.Store.BookStore.service.Impl;

import com.Store.BookStore.exception.UserDetailsException;
import com.Store.BookStore.model.BookInventory;
import com.Store.BookStore.repository.BookInventoryRepository;
import com.Store.BookStore.service.BookInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

        import javax.transaction.Transactional;
        import java.util.List;
        import java.util.Optional;

@Service
@Transactional
public class BookInventoryServiceImpl implements BookInventoryService {

    @Autowired
    private BookInventoryRepository bookInventoryRepository;

    @Override
    public BookInventory addBook(BookInventory book){

        return bookInventoryRepository.save(book);

    }

    @Override
    public List<BookInventory> getBooks(){
        return this.bookInventoryRepository.findAll();
    }

    @Override
    public BookInventory getBookByCode(String bookCode){
        Optional<BookInventory> userObj = this.bookInventoryRepository.findById(bookCode);

        if(userObj.isPresent()) {
            return userObj.get();
        } else{
            throw new UserDetailsException("Book not found with code: " + bookCode);
        }

    }

}

