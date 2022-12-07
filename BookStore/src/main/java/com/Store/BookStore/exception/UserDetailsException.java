package com.Store.BookStore.exception;

public class UserDetailsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserDetailsException(String message){
        super(message);
    }

    public UserDetailsException(String message, Throwable throwable){
        super(message,throwable);
    }
}
